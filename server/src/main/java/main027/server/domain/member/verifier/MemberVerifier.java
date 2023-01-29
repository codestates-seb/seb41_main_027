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

    public void verifyExistsEmailAndNickName(Member member) {
        String email = member.getEmail();
        String nickName = member.getNickName();

        Optional<Member> memberByEmail = memberRepository.findByEmail(email);
        Optional<Member> memberByNickName = memberRepository.findByNickName(nickName);
        if (memberByEmail.isPresent() && memberByNickName.isPresent())
            throw new BusinessLogicException(ExceptionCode.EMAIL_NICKNAME_ALREADY_EXISTS);
        if (memberByEmail.isPresent())
            throw new BusinessLogicException(ExceptionCode.EMAIL_ALREADY_EXISTS);
        if (memberByNickName.isPresent())
            throw new BusinessLogicException(ExceptionCode.NICKNAME_ALREADY_EXISTS);


    }

    public void verifyExistsNickName(String nickName) {
        Optional<Member> memberByNickName = memberRepository.findByNickName(nickName);
        if (memberByNickName.isPresent())
            throw new BusinessLogicException(ExceptionCode.NICKNAME_ALREADY_EXISTS);
    }
}
