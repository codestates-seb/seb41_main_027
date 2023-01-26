package main027.server.global.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenizer {
    @Getter
    @Value("${JWT_SECRET_KEY}")
    private String secretKey;

    @Getter
    @Value("${ACCESS_TOKEN_EXPIRATION_MINUTE}")
    private int accessTokenExpirationMinutes;

    @Getter
    @Value("${REFRESH_TOKEN_EXPIRATION_MINUTE}")
    private int refreshTokenExpirationMinutes;

    /** secretKey을 Base64 로 인코딩 하는 메서드 */
    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /** Jwt 토큰을 만들어 주는 메서드 */
    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    /** Refresh Token을 생성하는 메서드 */
    public String generateRefreshToken(String subject,
                                       Date expiration,
                                       String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    /** base64로 인코딩 된 SecretKey를 이용해 Jwt를 디코딩하는 메서드(parseClaimsJws(jws)에서 Signature를 검증하고 만료 기한까지 체크를 함)*/
    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
        return claims;
    }

    /** Jwt expire타임을 생성해주는 메서드 */
    public Date getTokenExpiration(int expirationMinute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinute);
        Date expiration = calendar.getTime();
        return expiration;
    }

    /** Jwt의 서명에 사용할 Secret Key 생성 */
    public Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] decodedKey = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(decodedKey);
        return key;
    }

    /** 토큰을 가지고 토큰 안의 subject를 추출하는 메서드 */
    public String getSubject(String token) {
        String subject = Jwts
                .parserBuilder()
                .setSigningKey(getKeyFromBase64EncodedKey(encodeBase64SecretKey(getSecretKey())))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return subject;
    }
}

