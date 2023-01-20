package main027.server.domain.member.repository;

import main027.server.domain.member.entity.Member;
import main027.server.global.aop.logging.annotation.TimeTrace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @TimeTrace
    Optional<Member> findByEmail(String email);

    @TimeTrace
    Optional<Member> findByNickName(String nickName);
}
