package main027.server.domain.member.service;

import main027.server.domain.member.entity.Member;
import main027.server.domain.member.repository.MemberRepository;
import main027.server.global.CustomBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import java.util.Optional;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final CustomBeanUtils<Member> beanUtils;

    public MemberService(MemberRepository memberRepository, CustomBeanUtils<Member> beanUtils) {
        this.memberRepository = memberRepository;
        this.beanUtils = beanUtils;
    }

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());

        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        Member verifiedMember = findVerifiedMember(member.getId());

        Member updatedMember = beanUtils.copyNonNullProperties(member, verifiedMember);

        return memberRepository.save(updatedMember);
    }

    public void deleteMember(long memberId) {
        Member verifiedMember = findVerifiedMember(memberId);
        memberRepository.delete(verifiedMember);
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new RuntimeException("Member Not Found")); // 예외처리 리팩토링 필요.

        return findMember;
    }

    public void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new RuntimeException("Member Already Exists"); // 예외처리 리팩토링 필요.
    }
}
