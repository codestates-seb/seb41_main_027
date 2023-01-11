package main027.server.domain.member.controller;

import com.google.gson.Gson;
import main027.server.domain.member.dto.MemberDto;
import main027.server.domain.member.entity.Member;
import main027.server.domain.member.mapper.MemberMapper;
import main027.server.domain.member.service.MemberServiceImpl;
import main027.server.domain.member.service.MemberUpdateServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberServiceImpl memberService;
    @MockBean
    private MemberUpdateServiceImpl memberUpdateService;

    @MockBean
    private MemberMapper mapper;

    @Test
    void postMember() throws Exception {
        // given
        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com", "HongGilDong", "12345678");

        String content = gson.toJson(post);

        MemberDto.Response responseDto = new MemberDto.Response(1L,
                                                                "hgd@gmail.com",
                                                                "HongGilDong",
                                                                Member.MemberStatus.ACTIVE,
                                                                List.of("ROLE_USER"),
                                                                LocalDateTime.now(),
                                                                LocalDateTime.now());

        given(mapper.memberPostDtoToMember(Mockito.any(MemberDto.Post.class))).willReturn(new Member());
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(new Member());
        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        System.out.println(actions.andReturn().getResponse().getContentAsString());

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value(responseDto.getEmail()))
                .andExpect(jsonPath("$.nickName").value(responseDto.getNickName()))
                .andExpect(jsonPath("$.memberStatus").value(responseDto.getNickName()))
                .andExpect(jsonPath("$.roles").isArray());
    }

    @Test
    void patchMember() throws Exception {
        // given
        long id = 1L;
        MemberDto.Patch patch = new MemberDto.Patch(1L, "HongGilDong", "12345678", Member.MemberStatus.ACTIVE);

        String content = gson.toJson(patch);

        MemberDto.Response responseDto = new MemberDto.Response(1L,
                                                                "hgd@gmail.com",
                                                                "HongGilDong",
                                                                Member.MemberStatus.ACTIVE,
                                                                List.of("ROLE_USER"),
                                                                LocalDateTime.now(),
                                                                LocalDateTime.now());

        given(mapper.memberPatchDtoToMember(Mockito.any(MemberDto.Patch.class))).willReturn(new Member());
        given(memberUpdateService.updateMember(Mockito.any(Member.class))).willReturn(new Member());
        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        patch("/members/{memberId}", id)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );
        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value(patch.getMemberId()))
                .andExpect(jsonPath("$.nickName").value(patch.getNickName()))
                .andExpect(jsonPath("$.nickName").value(patch.getNickName()));

    }

    @Test
    void deleteMember() throws Exception {
        long id = 1L;
        doNothing().when(memberService).deleteMember(Mockito.anyLong());

        ResultActions actions = mockMvc.perform(
                delete("/members/{memberId}", id)
        );

        actions
                .andExpect(status().isNoContent());
    }
}