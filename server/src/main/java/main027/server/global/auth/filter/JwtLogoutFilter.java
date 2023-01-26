package main027.server.global.auth.filter;

import lombok.RequiredArgsConstructor;
import main027.server.global.auth.Redis.RedisService;
import main027.server.global.auth.jwt.JwtTokenizer;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtLogoutFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer;
    private final RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestAccessToken = request.getHeader("Authorization");
        String subject = jwtTokenizer.getSubject(requestAccessToken.replace("Bearer", ""));
        long expiration = jwtTokenizer.getAccessTokenExpirationMinutes();
        redisService.setBlackList(requestAccessToken, subject, expiration);

        String requestRefreshToken = request.getHeader("Refresh");
        redisService.deleteRefreshToken(requestRefreshToken);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("logout Complete.");
        response.setHeader("Location", "/auth/login");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestAccessToken = request.getHeader("Authorization");
        String requestRefreshToken = request.getHeader("Refresh");
        String uri = request.getRequestURI();

        return requestAccessToken == null || requestRefreshToken == null || !(uri.equals("/auth/logout")) || !(request.getMethod().equals("POST"));
    }
}
