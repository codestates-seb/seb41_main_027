package main027.server.global.auth.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 유저 권한 & 유저 권한 정보 생성
 */
@Component
public class CustomAuthorityUtils {
    @Value("${ADMIN_EMAIL}")
    private String adminMail;
    private final List<String> ADMIN_ROLES_STRING = List.of("ADMIN", "USER");
    private final List<String> USER_ROLES_STRING = List.of("USER");

    /**
     * DB에 저장된 Role을 기반으로 유저 권한 정보 생성
     * @param roles 유저 권한
     * @return 유저 권한 정보
     */
    public List<GrantedAuthority> createAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
        return authorities;
    }

    /**
     * <p>member의 email을 통해 관리자 or 유저 권한 생성</p>
     * <p>회원 등록 시 입력한 이메일이 관리자 이메일과 같으면 관리자 권한이 부여 됨</p>
     * @param email(회원 등록 시 이메일)
     * @return 권한 정보
     */
    public List<String> createRoles(String email) {
        if (email.equals(adminMail)) {
            return ADMIN_ROLES_STRING;
        }
        return USER_ROLES_STRING;
    }
}
