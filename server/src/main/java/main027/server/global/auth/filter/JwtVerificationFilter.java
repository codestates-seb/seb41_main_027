package main027.server.global.auth.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import main027.server.global.advice.exception.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import main027.server.global.advice.exception.TokenException;
import main027.server.global.auth.Redis.RedisService;
import main027.server.global.auth.jwt.JwtTokenizer;
import main027.server.global.auth.utils.CustomAuthorityUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * JWT 검증 필터
 * OncePerRequestFilter는 request 당 한 번만 수행됨
 * 성공이냐 실패냐 한 번만 판단하면 되기 때문.
 */
@Slf4j
@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final RedisService redisService;

    /**
     * <p>Claims가 정상적으로 파싱되면 서명 검증 역시 자연스럽게 성공한 것임(별도의 검증 메서드가 필요 없음)</p>
     * <p>setAuthenticationToContext()는 Authentication 객체를 SecurityContext에 저장하기 위한 메서드임</p>
     * <p>JWT에 대한 서명 검증에 실패한 경우 발생하는 Exception을 처리할 수 있는 예외처리 로직 포함</p>
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            /** BlackList에 등록된 AccessToken인지 검증 */
            String blackList = redisService.getToken(request.getHeader("Authorization"));
            if (blackList != null) throw new TokenException(ExceptionCode.LOGOUT_MEMBER);

            Map<String, Object> claims = verifyJws(request);
            setAuthenticationToContext(claims);
        } catch (SignatureException se) {
            request.setAttribute("exception", se);
        } catch (ExpiredJwtException ee) {
            request.setAttribute("exception", ee);
        } catch (TokenException te){
            request.setAttribute("exception", te);
        } catch (Exception e) {
            request.setAttribute("exception", e);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * <p>OncePerRequestFilter의 shouldNotFilter를 오버라이드 한 것.</p>
     * <p>특정 조건에 부합하면(true) 해당 Filter의 동작을 수행하지 않고 건너뜀</p>
     * <p>JWT가 Authorization header에 포함되지 않았다면, JWT 자격 증명이 필요하지 않은 리로스에 대한 요청이라고 판단하여 다음 Filter로 처리를 넘김</p>
     *
     * @return true or false
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");
        return authorization == null || !authorization.startsWith("Bearer");
    }

    /**
     * <p>jws는 JWT Signed의 줄임말.</p>
     * <p>Bearer는 JWT의 토큰 타입을 의미</p>
     * 토큰에서 Bearer를 분리하고 claims를 추출하는 과정에서 검증(서버에서 발급했는지 여부, 만료기한 체크)까지 함.
     * @param request
     * @return claims(Token에 포함된 정보)
     */
    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer", "");
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();



        return claims;
    }

    /**
     * <p>JWT에서 파싱한 Claims를 통해 username(email)을 얻음</p>
     * <p>JWT의 Claims에서 얻은 권한 정보를 기반으로 List<GrantedAuthority>를 생성</p>
     * <p>username과 List<GrantedAuthority>를 포함한 Authentication 객체를 생성</p>
     * <p>SecurityContext에 Authentication 객체를 저장</p>
     * @param claims
     */
    private void setAuthenticationToContext(Map<String, Object> claims) {
        String email = (String) claims.get("email");
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities((List) claims.get("roles"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("securityContext에 email ={}, authorities ={} 저장 완료", email, authorities);
    }

}
