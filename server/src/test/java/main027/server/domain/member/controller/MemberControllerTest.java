package main027.server.domain.member.controller;

import com.google.gson.Gson;
import main027.server.domain.member.dto.MemberDto;
import main027.server.domain.member.entity.Member;
import main027.server.domain.member.mapper.MemberMapper;
import main027.server.domain.member.service.MemberService;
import main027.server.domain.member.service.MemberUpdateService;
import main027.server.global.aop.logging.DataHolder;
import main027.server.global.config.InterceptorConfig;
import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = MemberController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {SecurityConfig.class,
                InterceptorConfig.class})}
)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;
    @MockBean
    private MemberUpdateService memberUpdateService;

    @MockBean
    private DataHolder dataHolder;

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
                .andExpect(jsonPath("$.email").value(post.getEmail()))
                .andExpect(jsonPath("$.nickName").value(post.getNickName()))
                .andDo(document("post-member",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        List.of(
                                                fieldWithPath("email").type(JsonFieldType.STRING).description("?????? email"),
                                                fieldWithPath("nickName").type(JsonFieldType.STRING).description("?????? ?????????"),
                                                fieldWithPath("password").type(JsonFieldType.STRING).description("?????? ????????????")
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("email").type(JsonFieldType.STRING).description("?????? email"),
                                                fieldWithPath("nickName").type(JsonFieldType.STRING).description("?????? ?????????"),
                                                fieldWithPath("roles").type(JsonFieldType.ARRAY).description("?????? ??????"),
                                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description(
                                                        "?????? ??????")
                                        )
                                )));
    }

    @Test
    void patchMember() throws Exception {
        // given
        MemberDto.Patch patch = new MemberDto.Patch("HongGilDong", "12345678");

        String content = gson.toJson(patch);

        MemberDto.Response responseDto = new MemberDto.Response(1L,
                                                                "hgd@gmail.com",
                                                                "HongGilDong",
                                                                List.of("ROLE_USER"),
                                                                LocalDateTime.now(),
                                                                LocalDateTime.now());

        given(mapper.memberPatchDtoToMember(Mockito.any(), Mockito.anyLong())).willReturn(new Member());
        given(memberUpdateService.updateMember(Mockito.any(Member.class))).willReturn(new Member());
        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        patch("/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );
        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickName").value(patch.getNickName()))
                .andDo(document("patch-member",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        List.of(
                                                fieldWithPath("nickName").type(JsonFieldType.STRING).description("?????? ?????????"),
                                                fieldWithPath("password").type(JsonFieldType.STRING).description("?????? ????????????")
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("email").type(JsonFieldType.STRING).description("?????? email"),
                                                fieldWithPath("nickName").type(JsonFieldType.STRING).description("?????? ?????????"),
                                                fieldWithPath("roles").type(JsonFieldType.ARRAY).description("?????? ??????"),
                                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description(
                                                        "?????? ??????")
                                        )
                                )));

    }

    @Test
    void getMyInfo() throws Exception {
        //given
        MemberDto.Response responseDto = new MemberDto.Response(1L,
                                                                "hgd@gmail.com",
                                                                "HongGilDong",
                                                                List.of("ROLE_USER"),
                                                                LocalDateTime.now(),
                                                                LocalDateTime.now());

        given(memberService.findMember(Mockito.anyLong())).willReturn(new Member());
        given(mapper.memberToMemberResponseDto(Mockito.any())).willReturn(responseDto);

        //when
        ResultActions actions =
                mockMvc.perform(get("/members")
                                        .accept(MediaType.APPLICATION_JSON_UTF8));

        //then
        actions.andExpect(status().isOk())
                .andDo(document("get-myInfo",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        List.of(
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("email").type(JsonFieldType.STRING).description("?????? email"),
                                                fieldWithPath("nickName").type(JsonFieldType.STRING).description("?????? ?????????"),
                                                fieldWithPath("roles").type(JsonFieldType.ARRAY).description("?????? ??????"),
                                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description(
                                                        "?????? ??????")
                                        ))));
    }

    @Test
    void deleteMember() throws Exception {
        doNothing().when(memberService).deleteMember(Mockito.anyLong());

        ResultActions actions = mockMvc.perform(
                delete("/members")
        );

        actions
                .andExpect(status().isNoContent())
                .andDo(document("delete-member",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint())));
    }
}