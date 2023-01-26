package main027.server.domain.member.service;

import main027.server.domain.member.entity.Member;
import main027.server.domain.member.repository.MemberRepository;
import main027.server.domain.member.verifier.MemberVerifier;
import main027.server.global.aop.logging.annotation.TimeTrace;
import main027.server.global.auth.utils.CustomAuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberVerifier memberVerifier;

    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder,
                             CustomAuthorityUtils authorityUtils, MemberVerifier memberVerifier) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
        this.memberVerifier = memberVerifier;
    }

    @TimeTrace
    public Member createMember(Member member) {
        memberVerifier.verifyExistsEmailAndNickName(member);

        /**
         * 패스워드 암호화
         */
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        /**
         * User Role 생성
         */
        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        return memberRepository.save(member);
    }

    @TimeTrace
    public Member findMember(long memberId) {
        return memberVerifier.findVerifiedMember(memberId);
    }

    @TimeTrace
    public void deleteMember(long memberId) {
        Member verifiedMember = memberVerifier.findVerifiedMember(memberId);
        memberRepository.delete(verifiedMember);
    }
}
