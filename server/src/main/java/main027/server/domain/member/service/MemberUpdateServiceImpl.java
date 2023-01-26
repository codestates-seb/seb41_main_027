package main027.server.domain.member.service;

import main027.server.domain.member.entity.Member;
import main027.server.domain.member.repository.MemberRepository;
import main027.server.domain.member.verifier.MemberVerifier;
import main027.server.global.aop.logging.annotation.TimeTrace;
import main027.server.global.auth.utils.CustomAuthorityUtils;
import main027.server.global.utils.CustomBeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberUpdateServiceImpl implements MemberUpdateService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberVerifier memberVerifier;
    private final CustomBeanUtils<Member> beanUtils;

    public MemberUpdateServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder,
                                   CustomAuthorityUtils authorityUtils, MemberVerifier memberVerifier,
                                   CustomBeanUtils beanUtils) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
        this.memberVerifier = memberVerifier;
        this.beanUtils = beanUtils;
    }

    @TimeTrace
    public Member updateMember(Member member) {
        Member verifiedMember = memberVerifier.findVerifiedMember(member.getMemberId());

        if (member.getNickName() != null) {
            memberVerifier.verifyExistsNickName(member.getNickName());
        }

        if (member.getPassword() != null) {
            String encryptedPassword = passwordEncoder.encode(member.getPassword());
            member.setPassword(encryptedPassword);
        }

        Member updatedMember = beanUtils.copyNonNullProperties(member, verifiedMember);


        return memberRepository.save(updatedMember);
    }
}
