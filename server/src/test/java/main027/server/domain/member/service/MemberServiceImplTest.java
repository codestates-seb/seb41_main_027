package main027.server.domain.member.service;

import main027.server.domain.member.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {
    @Mock
    private MemberServiceImpl memberService;


    @Test
    public void createMember() {
        // given
        Member member = new Member("test@gmail.com", "11111111", "test");
        member.setCreatedAt(LocalDateTime.now());
        member.setModifiedAt(LocalDateTime.now());

        // when
        given(memberService.createMember(member)).willReturn(member);

        // then
        Assertions.assertThat(member).isEqualTo(memberService.createMember(member));
    }

    @Test
    public void findMember() {
        // given
        Member member = new Member("test@gmail.com", "11111111", "test");
        member.setCreatedAt(LocalDateTime.now());
        member.setModifiedAt(LocalDateTime.now());
        member.setMemberId(1L);

        // when
        given(memberService.findMember(member.getMemberId())).willReturn(member);

        // then
        Assertions.assertThat(member).isEqualTo(memberService.findMember(member.getMemberId()));
    }
}