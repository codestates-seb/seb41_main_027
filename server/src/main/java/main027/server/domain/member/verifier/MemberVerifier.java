package main027.server.domain.member.verifier;

import main027.server.domain.member.entity.Member;
import main027.server.domain.member.repository.MemberRepository;
import main027.server.global.advice.exception.BusinessLogicException;
import main027.server.global.advice.exception.ExceptionCode;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MemberVerifier {
    private final MemberRepository memberRepository;

    public MemberVerifier(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                                                   new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    public void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_EXISTS);
    }
}
