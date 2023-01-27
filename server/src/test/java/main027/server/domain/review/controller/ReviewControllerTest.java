package main027.server.domain.review.controller;//package main027.server.domain.review.controller;


import com.google.gson.Gson;
import main027.server.domain.review.dto.ReviewDto;
import main027.server.domain.review.entity.Review;
import main027.server.domain.review.mapper.ReviewMapper;
import main027.server.domain.review.service.ReviewService;
import main027.server.global.aop.logging.DataHolder;
import main027.server.global.auth.jwt.JwtTokenizer;
import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = ReviewController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class, // 추가
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
)
@AutoConfigureMockMvc
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReviewService reviewService;
    @MockBean
    private ReviewMapper reviewMapper;
    @MockBean
    private DataHolder dataHolder;
    @MockBean
    private JwtTokenizer jwtTokenizer;
    @Autowired
    private Gson gson;

    @Test
    void postReviewTest() throws Exception {
        //given

        ReviewDto.Post reviewPost = new ReviewDto.Post(1L,
                                                       "맛있어요",
                                                       1L);
        String content = gson.toJson(reviewPost);

        ReviewDto.Response response = new ReviewDto.Response(1L,
                                                             1L,
                                                             1L,
                                                             1L,
                                                             "맛있어요",
                                                             LocalDateTime.now());

        /**
         * PostToEntity에 dataHolder.getMemberId를 집어넣어서 테스트 할 생각이었으나, 저장된 값이 없어서 불러올 수 없음으로 추정
         */
        given(reviewMapper.PostToEntity(Mockito.any(), Mockito.anyLong())).willReturn(new Review());
        given(reviewService.save(Mockito.any(Review.class))).willReturn(new Review());
        given(reviewMapper.entityToResponse(Mockito.any(Review.class))).willReturn(response);

        //when
        ResultActions actions =
                mockMvc.perform(post("/reviews")
                                        .with(csrf())
                                        .accept(MediaType.APPLICATION_JSON_UTF8)
                                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                                        .content(content)
                );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.reviewId").isNumber())
                .andExpect(jsonPath("$.memberId").value(1L))
                .andExpect(jsonPath("$.placeId").value(1L))
                .andExpect(jsonPath("$.emojiId").value(1L))
                .andExpect(jsonPath("$.content").value(reviewPost.getContent()))
                .andDo(document("post-review",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        List.of(
                                                fieldWithPath("placeId").type(JsonFieldType.NUMBER).description("장소 식별자"),
                                                fieldWithPath("content").type(JsonFieldType.STRING).description("리뷰 내용"),
                                                fieldWithPath("emojiId").type(JsonFieldType.NUMBER).description("이모지 식별자")
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("reviewId").type(JsonFieldType.NUMBER).description("리뷰 식별자"),
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                                fieldWithPath("placeId").type(JsonFieldType.NUMBER).description("장소 식별자"),
                                                fieldWithPath("content").type(JsonFieldType.STRING).description("리뷰 내용"),
                                                fieldWithPath("emojiId").type(JsonFieldType.NUMBER).description("이모지 식별자"),
                                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간")
                                        )
                                )));
    }

    @Test
    void getPlaceReviewsTest() throws Exception {
        //given
        Long id = 1L;

        List<ReviewDto.Response> responses = List.of(
                new ReviewDto.Response(1L,
                                       1L,
                                       1L,
                                       1L,
                                       "연인과 함께하기 좋아요",
                                       LocalDateTime.of(2022, 11, 11, 11, 11)),
                new ReviewDto.Response(2L,
                                       2L,
                                       1L,
                                       5L,
                                       "바퀴벌레가 나왔어요",
                                       LocalDateTime.of(2022, 12, 25, 12, 25))
        );

        ReviewDto.ListResponse listResponse = new ReviewDto.ListResponse();
        listResponse.setReviewList(responses);
        listResponse.setPresentPage(1L);
        listResponse.setTotalPages(1L);
        listResponse.setTotalElements(2L);

        Page<Review> reviewPage = new PageImpl<>(List.of());

        given(reviewService.findReviews(Mockito.anyLong(), eq(Pageable.ofSize(10)))).willReturn(reviewPage);
        given(reviewMapper.pageToList(Mockito.any())).willReturn(listResponse);

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/reviews/{placdId}", id)
                                .param("page", "1")
                                .param("size", "10")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document("get-reviews",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestParameters(
                                        parameterWithName("page").description("페이지"),
                                        parameterWithName("size").description("페이지 크기")
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("reviewList").type(JsonFieldType.ARRAY).description("결과 데이터").optional(),
                                                fieldWithPath("reviewList[].reviewId").type(JsonFieldType.NUMBER).description("리뷰 식별자"),
                                                fieldWithPath("reviewList[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                                fieldWithPath("reviewList[].placeId").type(JsonFieldType.NUMBER).description("장소 식별자"),
                                                fieldWithPath("reviewList[].emojiId").type(JsonFieldType.NUMBER).description("이모지 식별자"),
                                                fieldWithPath("reviewList[].content").type(JsonFieldType.STRING).description("리뷰 내용"),
                                                fieldWithPath("reviewList[].createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                                fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수"),
                                                fieldWithPath("presentPage").type(JsonFieldType.NUMBER).description("현재 페이지 수"),
                                                fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("등록된 장소 수")
                                        )
                                )));
    }

    // @Test
    // void deleteReviewTest() throws Exception {
    //     given
        // Long id = 1L;
        // doNothing().when(reviewService).remove(Mockito.anyLong(),Mockito.anyLong());
        //
        // when
        // ResultActions actions =
        //         mockMvc.perform(delete("/reviews/{reviewId}", id));
        //
        // then
        // actions
        //         .andExpect(status().isNoContent())
        //         .andDo(document("delete-review",
        //                         preprocessRequest(prettyPrint()),
        //                         preprocessResponse(prettyPrint()),
        //                         pathParameters(
        //                                 parameterWithName("reviewId").description("리뷰 식별자")
        //                         )
        //         ));
    // }
}