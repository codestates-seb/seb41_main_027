package main027.server.global.auth.userdetails;

import main027.server.domain.member.entity.Member;
import main027.server.domain.member.repository.MemberRepository;
import main027.server.global.auth.utils.CustomAuthorityUtils;
import main027.server.global.exception.BusinessLogicException;
import main027.server.global.exception.ExceptionCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;

    public MemberDetailsService(MemberRepository memberRepository, CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.authorityUtils = authorityUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByEmail(username);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return new MemberDetails(findMember);
    }

    /**
     * <p>MemberDetails는 UserDetails를 구현하고 Member 엔티티 클래스를 상속함.</p>
     * <p>데이터베이스에서 조회한 회원 정보를 Spring Security의 User 정보로 변환하는 과정과</p>
     * <p>User의 권한 정보를 생성하는 과정을 캡슐화할 수 있다.</p>
     */
    private final class MemberDetails extends Member implements UserDetails {
        MemberDetails(Member member) {
            setMemberId(member.getMemberId());
            setNickName(member.getNickName());
            setEmail(member.getEmail());
            setPassword(member.getPassword());
            setRoles(member.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
