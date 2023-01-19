package main027.server.domain.review.controller;


import com.google.gson.Gson;
import main027.server.domain.place.controller.PlaceController;
import main027.server.domain.place.mapper.PlaceMapper;
import main027.server.domain.place.service.PlaceService;
import main027.server.domain.place.service.PlaceServiceImpl;
import main027.server.domain.place.service.PlaceUpdateService;
import main027.server.domain.review.dto.ReviewDto;
import main027.server.domain.review.entity.Review;
import main027.server.domain.review.mapper.ReviewMapper;
import main027.server.domain.review.service.ReviewService;
import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(
//        controllers = ReviewController.class,
//        excludeAutoConfiguration = SecurityAutoConfiguration.class, // 추가
//        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
//)
@SpringBootTest
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
    @Autowired
    private Gson gson;

    @Test
    void postReviewTest() throws Exception {
        //given
        ReviewDto.Post reviewPost = new ReviewDto.Post(1L,
                                                       1L,
                                                       "맛있어요",
                                                       1L);
        String content = gson.toJson(reviewPost);

        ReviewDto.Response response = new ReviewDto.Response(1L,
                                                             1L,
                                                             1L,
                                                             1L,
                                                             "맛있어요",
                                                             LocalDateTime.now());

        given(reviewMapper.PostToEntity(Mockito.any(ReviewDto.Post.class))).willReturn(new Review());
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
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
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
}
