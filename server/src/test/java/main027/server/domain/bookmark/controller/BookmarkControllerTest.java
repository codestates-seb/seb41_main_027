//package main027.server.domain.bookmark.controller;
//
//import com.google.gson.Gson;
//import main027.server.domain.bookmark.dto.BookmarkDto;
//import main027.server.domain.bookmark.entity.Bookmark;
//import main027.server.domain.bookmark.mapper.BookmarkMapper;
//import main027.server.domain.bookmark.service.BookmarkService;
//import main027.server.domain.place.controller.PlaceController;
//import main027.server.domain.place.dto.PlaceDto;
//import main027.server.domain.place.entity.Place;
//import main027.server.domain.place.mapper.PlaceMapper;
//import main027.server.domain.place.service.PlaceService;
//import main027.server.domain.place.service.PlaceUpdateService;
//import org.apache.catalina.security.SecurityConfig;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.*;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
////@WebMvcTest(
////        controllers = PlaceController.class,
////        excludeAutoConfiguration = SecurityAutoConfiguration.class, // 추가
////        excludeFilters = {
////                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
////)
//@SpringBootTest
//@AutoConfigureMockMvc
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//public class BookmarkControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private BookmarkService bookmarkService;
//    @MockBean
//    private BookmarkMapper bookmarkMapper;
//
//    @Autowired
//    private Gson gson;
//
//    /**
//     * Bookmark Post 컨트롤러로 요청이 들어갔을 때 북마크를 저장했는지 확인하는 통합 테스트
//     * 성공적으로 저장이 됬다면 true를 반환
//     */
//    @Test
//    public void postBookmark() throws Exception {
//        //given
//        BookmarkDto.Post postDto = new BookmarkDto.Post(1L);
//        String content = gson.toJson(postDto);
//
//        List<PlaceDto.PlaceResponseDto> responseDtos = List.of(
//                new PlaceDto.PlaceResponseDto(1L,
//                                              1L,
//                                              "강남역7번출구맥도날드",
//                                              "서울시 강남구",
//                                              "존나 맛없어요",
//                                              1,
//                                              1,
//                                              true,
//                                              true,
//                                              1L,
//                                              "햄버거",
//                                              "37",
//                                              "127"),
//
//                new PlaceDto.PlaceResponseDto(2L,
//                                              1L,
//                                              "건대역2번출구맥도날드",
//                                              "서울시 광진구",
//                                              "존나 맛있어요",
//                                              1,
//                                              1,
//                                              true,
//                                              true,
//                                              1L,
//                                              "햄버거",
//                                              "37",
//                                              "127"),
//
//        BookmarkDto.Response new PlaceDto.PlaceResponseDto();
//        response.setPlaceList(responseDtos);
//        response.setPresentPage(1L);
//        response.setTotalPages(10L);
//        response.setTotalElements(50L);
//
//        Page<Place> placePage = new PageImpl<>(List.of());
//
//        given(bookmarkService.findPlaceMemberBookmarked(postDto.getMemberId(), Pageable.ofSize(10))).willReturn(placePage);
//        given(bookmarkMapper.pageToList(Mockito.any())).willReturn(response);
//        given(bookmarkService.changeBookmarkStatus(Mockito.any())).willReturn(true);
//
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                post("/bookmarks")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//        );
//
//        //then
//        actions.andExpect(status().isOk())
//               .andExpect(content().string("true"))
//               .andDo(document("post-bookmark",
//                               preprocessRequest(prettyPrint()),
//                               preprocessResponse(prettyPrint()),
//                               requestFields(
//                                       List.of(
//                                       fieldWithPath("placeId").type(JsonFieldType.NUMBER).description("장소 식별자"),
//                                       fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자")
//                               )),
//
//                               responseFields(
//                                       List.of(
//                                               fieldWithPath("placeList").type(JsonFieldType.ARRAY).description("결과 데이터").optional(),
//                                               fieldWithPath("placeList[].placeId").type(JsonFieldType.NUMBER).description("장소 식별자"),
//                                               fieldWithPath("placeList[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                               fieldWithPath("placeList[].name").type(JsonFieldType.STRING).description("장소 이름"),
//                                               fieldWithPath("placeList[].address").type(JsonFieldType.STRING).description("장소 주소"),
//                                               fieldWithPath("placeList[].description").type(JsonFieldType.STRING).description(
//                                                       "장소 설명"),
//                                               fieldWithPath("placeList[].placeLikeUserList").type(JsonFieldType.ARRAY).description("좋아요 회원 리스트"),
//                                               fieldWithPath("placeList[]isBookMarked").type(JsonFieldType.BOOLEAN).description("북마크 여부 확인"),
//                                               fieldWithPath("placeList[].kakaoId").type(JsonFieldType.NUMBER).description("카카오맵 식별자"),
//                                               fieldWithPath("placeList[].category").type(JsonFieldType.STRING).description(
//                                                       "카테고리"),
//                                               fieldWithPath("placeList[].latitude").type(JsonFieldType.STRING).description("위도"),
//                                               fieldWithPath("placeList[].longitude").type(JsonFieldType.STRING).description("경도"),
//                                               fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수"),
//                                               fieldWithPath("presentPage").type(JsonFieldType.NUMBER).description("현재 페이지 수"),
//                                               fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("등록된 장소 수")
//                                       )
//                               )));
//    }
//
//    @Test
//    void getBookmarkList() throws Exception {
//        //given
//        BookmarkDto.Post postDto = new BookmarkDto.Post(1L);
//
//        List<PlaceDto.PlaceResponseDto> responseDtos = List.of(
//                new PlaceDto.PlaceResponseDto(1L,
//                                              1L,
//                                              "강남역7번출구맥도날드",
//                                              "서울시 강남구",
//                                              "존나 맛없어요",
//                                              3,
//                                              3,
//                                              true,
//                                              true,
//                                              0L,
//                                              "햄버거",
//                                              "37",
//                                              "127"),
//
//                new PlaceDto.PlaceResponseDto(2L,
//                                              1L,
//                                              "건대역2번출구맥도날드",
//                                              "서울시 광진구",
//                                              "존나 맛있어요",
//                                              4,
//                                              3,
//                                              true,
//                                              true,
//                                              0L,
//                                              "햄버거",
//                                              "37",
//                                              "127"));
//
//        BookmarkDto.Response response = new BookmarkDto.Response();
//        response.setPlaceList(responseDtos);
//        response.setPresentPage(1L);
//        response.setTotalPages(10L);
//        response.setTotalElements(50L);
//
//        Page<Place> placePage = new PageImpl<>(List.of());
//
//        given(bookmarkService.findPlaceMemberBookmarked(postDto.getMemberId(), Pageable.ofSize(10))).willReturn(placePage);
//        given(bookmarkMapper.pageToList(Mockito.any())).willReturn(response);
//
//        Long Id = 1L;
//
//        //when
//        ResultActions actions = mockMvc.perform(
//                get("/bookmarks/{memberId}", Id)
//                        .accept(MediaType.APPLICATION_JSON_UTF8)
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//        );
//
//        //then
//        actions.andExpect(status().isOk())
//               .andExpect(jsonPath("$.presentPage").value(1))
//               .andExpect(jsonPath("$.placeList").exists())
//               .andDo(document("get-bookmarkList",
//                               preprocessRequest(prettyPrint()),
//                               preprocessResponse(prettyPrint()),
//                               pathParameters(
//                                       parameterWithName("memberId").description("회원 식별자")
//                               ),
//
//                               responseFields(
//                                       List.of(
//                                               fieldWithPath("placeList").type(JsonFieldType.ARRAY).description("결과 데이터").optional(),
//                                               fieldWithPath("placeList[].placeId").type(JsonFieldType.NUMBER).description("장소 식별자"),
//                                               fieldWithPath("placeList[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
//                                               fieldWithPath("placeList[].name").type(JsonFieldType.STRING).description("장소 이름"),
//                                               fieldWithPath("placeList[].address").type(JsonFieldType.STRING).description("장소 주소"),
//                                               fieldWithPath("placeList[].description").type(JsonFieldType.STRING).description(
//                                                       "장소 설명"),
//                                               fieldWithPath("placeList[].likeCount").type(JsonFieldType.NUMBER).description("좋아요 숫자"),
//                                               fieldWithPath("placeList[].reviewCount").type(JsonFieldType.NUMBER).description("리뷰 갯수"),
//                                               fieldWithPath("placeList[]isBookMarked").type(JsonFieldType.BOOLEAN).description("북마크 여부 확인"),
//                                               fieldWithPath("placeList[]isLiked").type(JsonFieldType.BOOLEAN).description("좋아요 여부 확인"),
//                                               fieldWithPath("placeList[].kakaoId").type(JsonFieldType.NUMBER).description("카카오맵 식별자"),
//                                               fieldWithPath("placeList[].category").type(JsonFieldType.STRING).description(
//                                                       "카테고리"),
//                                               fieldWithPath("placeList[].latitude").type(JsonFieldType.STRING).description("위도"),
//                                               fieldWithPath("placeList[].longitude").type(JsonFieldType.STRING).description("경도"),
//                                               fieldWithPath("placeList[].createdAt").type(JsonFieldType.STRING).description("생성 시간"),
//                                               fieldWithPath("placeList[].modifiedAt").type(JsonFieldType.STRING).description("수정 시간"),
//                                               fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수"),
//                                               fieldWithPath("presentPage").type(JsonFieldType.NUMBER).description("현재 페이지 수"),
//                                               fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("등록된 장소 수")
//                                       )
//                               )));
//
//    }
//
////    private void addBookmark() throws Exception {
////        BookmarkDto.Post postDto = new BookmarkDto.Post(1L, 1L);
////        String content = gson.toJson(postDto);
////
////        mockMvc.perform(
////                post("/bookmarks")
////                        .accept(MediaType.APPLICATION_JSON)
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(content)
////        );
////    }
//
//}
