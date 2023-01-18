package main027.server.global.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import main027.server.domain.member.entity.Member;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * (7) 로그인 인증 성공 시 추가작업을 할 수 있는 성공 핸들러
 * 인증 성공 후, 로그를 기록하거나 사용자 정보를 response로 전송
 */
@Slf4j
public class MemberAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        Member member = (Member) authentication.getPrincipal();
        ObjectMapper objectMapper = new ObjectMapper();

        LinkedHashMap<String, Object> loginResponse = new LinkedHashMap<>();
        loginResponse.put("memberId", member.getMemberId());
        loginResponse.put("email", member.getEmail());
        loginResponse.put("nickName", member.getNickName());
        loginResponse.put("roles", member.getRoles());

        String responsebody = objectMapper.writeValueAsString(loginResponse);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        response.getWriter().write(responsebody);

        log.info("# Authenticated successfully!");
        log.info("nickName: {}, email: {}, role: {}", member.getNickName(), member.getEmail(), member. getRoles());
    }
}
