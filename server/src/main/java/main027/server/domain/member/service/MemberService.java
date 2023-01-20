package main027.server.domain.member.service;

import main027.server.domain.member.entity.Member;

public interface MemberService {
    Member createMember(Member member);
    Member findMember(long memberId);
    void deleteMember(long memberId);
}
