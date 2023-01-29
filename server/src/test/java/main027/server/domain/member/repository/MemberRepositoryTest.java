package main027.server.domain.member.repository;

import main027.server.domain.member.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    public MemberRepository memberRepository;

    @Test
    public void saveMemberTest() {
        // given
        Member testMember = new Member();
        testMember.setEmail("test@gmail.com");
        testMember.setPassword("11111111");
        testMember.setNickName("test");

        // when
        Member savedMember = memberRepository.save(testMember);

        // then
        Assertions.assertNotNull(savedMember);
        Assertions.assertTrue(testMember.getEmail().equals(savedMember.getEmail()));
        Assertions.assertTrue(testMember.getPassword().equals(savedMember.getPassword()));
        Assertions.assertTrue(testMember.getNickName().equals(savedMember.getNickName()));
    }

    @Test
    public void findByEmailTest() {
        // given
        Member testMember = new Member();
        testMember.setEmail("test1@gmail.com");
        testMember.setPassword("11111111");
        testMember.setNickName("test1");

        // when
        memberRepository.save(testMember);
        Optional<Member> findMember = memberRepository.findByEmail(testMember.getEmail());

        // them
        Assertions.assertTrue(findMember.isPresent());
        Assertions.assertTrue(findMember.get().getEmail().equals(testMember.getEmail()));
    }

    @Test
    public void findByNicknameTest() {
        // given
        Member testMember = new Member();
        testMember.setEmail("test@gmail.com");
        testMember.setPassword("11111111");
        testMember.setNickName("test");

        // when
        memberRepository.save(testMember);
        Optional<Member> findMember = memberRepository.findByNickName(testMember.getNickName());

        Assertions.assertTrue(findMember.isPresent());
        Assertions.assertTrue(findMember.get().getNickName().equals(testMember.getNickName()));
    }
}