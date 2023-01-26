package main027.server.global.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main027.server.global.aop.logging.DataHolder;
import main027.server.global.auth.jwt.JwtTokenizer;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class DataInterceptor implements HandlerInterceptor {

    private final JwtTokenizer jwtTokenizer;
    private final DataHolder dataHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        dataHolder.setMethod(request.getMethod());
        dataHolder.setUri(request.getRequestURI());
        if (request.getHeader("Authorization") == null) {
            log.info("로그인 되지 않은 사용자 요청");
            return true;
        }
        try {
            Map<String, Object> claims = verifyJws(request);
            dataHolder.setMemberId(Long.valueOf((Integer) claims.get("memberId")));
            log.info("로그인 된 사용자 요청");
        } catch (Exception e) {
            log.info("로그인 되지 않은 사용자 요청");
            return true;
        }
        return true;
    }

    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer", "");
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();

        return claims;
    }
}
