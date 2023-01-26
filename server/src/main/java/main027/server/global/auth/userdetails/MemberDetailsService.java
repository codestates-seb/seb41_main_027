package main027.server.global.auth.userdetails;

import main027.server.domain.member.entity.Member;
import main027.server.domain.member.repository.MemberRepository;
import main027.server.global.advice.exception.TokenException;
import main027.server.global.auth.utils.CustomAuthorityUtils;
import main027.server.global.advice.exception.BusinessLogicException;
import main027.server.global.advice.exception.ExceptionCode;
import main027.server.global.auth.utils.CustomAuthorityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

/** 사용자의 크리덴셜을 DB에서 조회한 후 AuthenricationManager에게 사용자의 MemberDetails를 전달 */
@Component
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;

    public MemberDetailsService(MemberRepository memberRepository, CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.authorityUtils = authorityUtils;
    }

    /**
     * 사용자의 정보를 DB에서 조회
     * @param email 이메일
     * @return MemberDetails 생성
     */
    @Override
    public MemberDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        Member findMember = optionalMember.orElseThrow(() -> new TokenException(ExceptionCode.UNREGISTERED_MEMBER));

        return new MemberDetails(findMember);
    }

    /**
     * <p>추가 설명 : MemberDetails는 UserDetails를 구현하고 Member 엔티티 클래스를 상속함.</p>
     * <p>데이터베이스에서 조회한 회원 정보를 Spring Security의 유저 정보로 변환하는 과정과</p>
     * <p>유저의 권한 정보를 생성하는 과정을 캡슐화할 수 있다.</p>
     */
    public class MemberDetails extends Member implements UserDetails {
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
