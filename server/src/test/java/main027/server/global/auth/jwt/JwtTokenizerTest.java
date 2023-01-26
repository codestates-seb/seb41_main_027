package main027.server.global.auth.jwt;


import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JwtTokenizerTest {
    private static JwtTokenizer jwtTokenizer;
    private String secretKey;
    private String base64EncodedSecretKey;

    @BeforeAll
    public void init() {
        jwtTokenizer = new JwtTokenizer();
        secretKey = "ecogreenseoul2023ecogreenseoul2023ecogreenseoul2023";

        base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(secretKey);
    }

    /** AccessToken 생성 테스트 */
    @Test
    void generateAccessTokenTest() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", 1);
        claims.put("roles", List.of("USER"));

        String subject = "AccessToken Test";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 10);
        Date expiration = calendar.getTime();

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        System.out.println(accessToken);

        Assertions.assertNotNull(accessToken);
    }

    /** RefreshToken 생성 테스트 */
    @Test
    void generateRefreshTokenTest() {
        String subject = "RefreshToken Test";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        Date expiration = calendar.getTime();

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        System.out.println(refreshToken);

        Assertions.assertNotNull(refreshToken);
    }

    /** 발급된 AccessToken으로부터 claims가 정상적으로 출력되면, 서버에서 발급해준 AccessToken임이 확인됨 */
    @Test
    void verifyAccessTokenSignatureTest() {
        String accessToken = getAccessToken(Calendar.MINUTE, 10);
        Assertions.assertDoesNotThrow(() -> jwtTokenizer.getClaims(accessToken, base64EncodedSecretKey));
    }

    /** AccessToken이 만료되면 정상적으로 claims를 리턴할 수 없음 */
    @Test
    void verifyAccessTokenExpirationTest() throws InterruptedException {
        String accessToken = getAccessToken(Calendar.SECOND, 1);

        TimeUnit.MILLISECONDS.sleep(1500);
        Assertions.assertThrows(ExpiredJwtException.class, () -> jwtTokenizer.getClaims(accessToken, base64EncodedSecretKey));
    }

    /** 발급된 RefreshToken으로부터 claims가 정상적으로 출력되면, 서버에서 발급해준 RefreshToken임이 확인됨 */
    @Test
    void verifyRefreshTokenSignatureTest() {
        String refreshToken = getRefreshToken(Calendar.MINUTE, 10);
        Assertions.assertDoesNotThrow(() -> jwtTokenizer.getClaims(refreshToken, base64EncodedSecretKey));
    }

    /** RefreshToken이 만료되면 정상적으로 claims를 리턴할 수 없음 */
    @Test
    void verifyRefreshTokenExpirationTest() throws InterruptedException {
        String refreshToken = getRefreshToken(Calendar.SECOND, 1);

        TimeUnit.MILLISECONDS.sleep(1500);
        Assertions.assertThrows(ExpiredJwtException.class, () -> jwtTokenizer.getClaims(refreshToken, base64EncodedSecretKey));
    }

    public String getAccessToken(int timeUnit, int timeAmount) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", 1);
        claims.put("roles", List.of("USER"));

        String subject = "Mock AccessToken";
        Calendar calendar = Calendar.getInstance();
        calendar.add(timeUnit, timeAmount);
        Date expiration = calendar.getTime();

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    public String getRefreshToken(int timeUnit, int timeAmount) {
        String subject = "RefreshToken Test";
        Calendar calendar = Calendar.getInstance();
        calendar.add(timeUnit, timeAmount);
        Date expiration = calendar.getTime();

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}