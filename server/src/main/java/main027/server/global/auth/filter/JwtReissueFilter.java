package main027.server.global.auth.filter;

import lombok.RequiredArgsConstructor;
import main027.server.global.advice.exception.ExceptionCode;
import main027.server.global.advice.exception.TokenException;
import main027.server.global.auth.Redis.RedisService;
import main027.server.global.auth.jwt.JwtTokenizer;
import main027.server.global.auth.userdetails.MemberDetailsService;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class JwtReissueFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer;
    private final RedisService redisService;
    private final MemberDetailsService memberDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String requestRefreshToken = request.getHeader("Refresh");
            MemberDetailsService.MemberDetails memberDetails = memberDetailsService.loadUserByUsername(
                    redisService.getToken(requestRefreshToken));

            String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
            jwtTokenizer.getClaims(requestRefreshToken, base64EncodedSecretKey);
            String newAccessToken = delegateNewAccessToken(memberDetails, base64EncodedSecretKey);

            response.setHeader("Authorization", "Bearer" + newAccessToken);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("Reissued Complete.");

        } catch (TokenException e) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(ExceptionCode.INVALID_REFRESH_TOKEN.getStatus());
            response.getWriter().write(ExceptionCode.INVALID_REFRESH_TOKEN.getMessage());
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Refresh");
        String uri = request.getRequestURI();

        return authorization == null || !(uri.equals("/reissue")) || !(request.getMethod().equals("POST"));
    }

    private String delegateNewAccessToken(MemberDetailsService.MemberDetails memberDetails, String base64EncodedSecretKey) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", memberDetails.getMemberId());
        claims.put("email", memberDetails.getEmail());
        claims.put("roles", memberDetails.getRoles());

        String subject = memberDetails.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }
}
