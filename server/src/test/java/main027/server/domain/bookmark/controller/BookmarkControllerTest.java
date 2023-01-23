package main027.server.domain.bookmark.controller;//package main027.server.domain.bookmark.controller;

import com.google.gson.Gson;
import main027.server.domain.bookmark.dto.BookmarkDto;
import main027.server.domain.bookmark.entity.Bookmark;
import main027.server.domain.bookmark.mapper.BookmarkMapper;
import main027.server.domain.bookmark.service.BookmarkService;
import main027.server.domain.place.controller.PlaceController;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.mapper.PlaceMapper;
import main027.server.domain.place.service.PlaceService;
import main027.server.domain.place.service.PlaceUpdateService;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = BookmarkController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class, // 추가
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
)
//@SpringBootTest
//@AutoConfigureMockMvc
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class BookmarkControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookmarkService bookmarkService;
    @MockBean
    private BookmarkMapper bookmarkMapper;
    @MockBean
    private PlaceMapper placeMapper;
    @MockBean
    private DataHolder dataHolder;
    @MockBean
    private JwtTokenizer jwtTokenizer;
    @Autowired
    private Gson gson;

    /**
     * Bookmark Post 컨트롤러로 요청이 들어갔을 때 북마크를 저장했는지 확인하는 통합 테스트
     * 성공적으로 저장이 됬다면 true를 반환
     */
    @Test
    public void postBookmark() throws Exception {
        //given
        Long id = 1L;
        BookmarkDto.Post postDto = new BookmarkDto.Post(1L);
        String content = gson.toJson(postDto);

        PlaceDto.PlaceResponseDto responseDto = new PlaceDto.PlaceResponseDto(3L,
                                                                              2L,
                                                                              "건대 길이식당",
                                                                              "서울특별시 광진구 화양동 광나루로24길 25-4",
                                                                              "한 번 맛보면 잊을 수 없는 그 맛!!",
                                                                              999,
                                                                              false,
                                                                              false,
                                                                              0L,
                                                                              "푸드",
                                                                              "37",
                                                                              "127",
                                                                              LocalDateTime.of(2023, 01, 20, 19, 27));

        given(bookmarkMapper.PostToEntity(Mockito.anyLong(),Mockito.anyLong())).willReturn(new Bookmark());
        given(bookmarkService.changeBookmarkStatus(Mockito.any())).willReturn(true);
        given(placeMapper.placeToPlaceResponseDto(Mockito.any(Place.class), Mockito.anyLong())).willReturn(responseDto);


        //when
        ResultActions actions = mockMvc.perform(
                post("/bookmarks/{placeId}", id)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(content)
        );

        //then
        actions.andExpect(status().isOk())
               .andExpect(content().string("true"))
               .andDo(document("post-bookmark",
                               preprocessRequest(prettyPrint()),
                               preprocessResponse(prettyPrint()),
                               requestFields(
                                       List.of(
                                       fieldWithPath("placeId").type(JsonFieldType.NUMBER).description("장소 식별자")
                               )),

                               responseFields(
                                       List.of(
                                               fieldWithPath("placeList").type(JsonFieldType.ARRAY).description("결과 데이터").optional(),
                                               fieldWithPath("placeList[].placeId").type(JsonFieldType.NUMBER).description("장소 식별자"),
                                               fieldWithPath("placeList[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                               fieldWithPath("placeList[].name").type(JsonFieldType.STRING).description("장소 이름"),
                                               fieldWithPath("placeList[].address").type(JsonFieldType.STRING).description("장소 주소"),
                                               fieldWithPath("placeList[].description").type(JsonFieldType.STRING).description(
                                                       "장소 설명"),
                                               fieldWithPath("placeList[].likeCount").type(JsonFieldType.NUMBER).description("좋아요 숫자"),
                                               fieldWithPath("placeList[].isBookMarked").type(JsonFieldType.BOOLEAN).description("북마크 여부 확인"),
                                               fieldWithPath("placeList[].isLiked").type(JsonFieldType.BOOLEAN).description("좋아요 여부 확인"),
                                               fieldWithPath("placeList[].kakaoId").type(JsonFieldType.NUMBER).description("카카오맵 식별자"),
                                               fieldWithPath("placeList[].category").type(JsonFieldType.STRING).description(
                                                       "카테고리"),
                                               fieldWithPath("placeList[].latitude").type(JsonFieldType.STRING).description("위도"),
                                               fieldWithPath("placeList[].longitude").type(JsonFieldType.STRING).description("경도"),
                                               fieldWithPath("placeList[].createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                               fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수"),
                                               fieldWithPath("presentPage").type(JsonFieldType.NUMBER).description("현재 페이지 수"),
                                               fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("등록된 장소 수")
                                       )
                               )));
    }

    @Test
    void getListTest() throws Exception {
        //given
        List<PlaceDto.PlaceResponseDto> responseDtos = List.of(
                new PlaceDto.PlaceResponseDto(1L,
                                              1L,
                                              "강남역7번출구맥도날드",
                                              "서울시 강남구",
                                              "존나 맛없어요",
                                              3,
                                              true,
                                              true,
                                              0L,
                                              "푸드",
                                              "37",
                                              "127",
                                              LocalDateTime.of(2023, 01, 19, 13, 20)),

                new PlaceDto.PlaceResponseDto(2L,
                                              1L,
                                              "건대역2번출구맥도날드",
                                              "서울시 광진구",
                                              "존나 맛있어요",
                                              4,
                                              true,
                                              true,
                                              0L,
                                              "푸드",
                                              "37",
                                              "127",
                                              LocalDateTime.of(2023,01,20,12,30)),
                new PlaceDto.PlaceResponseDto(3L,
                                              2L,
                                              "건대 길이식당",
                                              "서울특별시 광진구 화양동 광나루로24길 25-4",
                                              "한 번 맛보면 잊을 수 없는 그 맛!!",
                                              999,
                                              false,
                                              false,
                                              0L,
                                              "푸드",
                                              "37",
                                              "127",
                                              LocalDateTime.of(2023,01,20,19,27)));

        PlaceDto.PageResponseDto pageResponseDto = new PlaceDto.PageResponseDto();
        pageResponseDto.setPlaceList(responseDtos);
        pageResponseDto.setPresentPage(1L);
        pageResponseDto.setTotalPages(1L);
        pageResponseDto.setTotalElements(3L);

        Page<Place> placePage = new PageImpl<>(List.of());

        given(bookmarkService.findPlaceMemberBookmarked(Mockito.anyLong(), eq(Pageable.ofSize(10)))).willReturn(placePage);
        given(placeMapper.pageToList(Mockito.any(),Mockito.anyLong())).willReturn(pageResponseDto);

        //when
        ResultActions actions = mockMvc.perform(
                get("/bookmarks")
                        .param("page","1")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        );

        //then
        actions.andExpect(status().isOk())
               .andExpect(jsonPath("$.presentPage").value(1))
               .andExpect(jsonPath("$.placeList").exists())
               .andDo(document("get-bookmarkList",
                               preprocessRequest(prettyPrint()),
                               preprocessResponse(prettyPrint()),
                               requestParameters(
                                       parameterWithName("page").description("페이지")),
                               responseFields(
                                       List.of(
                                               fieldWithPath("placeList").type(JsonFieldType.ARRAY).description("결과 데이터").optional(),
                                               fieldWithPath("placeList[].placeId").type(JsonFieldType.NUMBER).description("장소 식별자"),
                                               fieldWithPath("placeList[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                               fieldWithPath("placeList[].name").type(JsonFieldType.STRING).description("장소 이름"),
                                               fieldWithPath("placeList[].address").type(JsonFieldType.STRING).description("장소 주소"),
                                               fieldWithPath("placeList[].description").type(JsonFieldType.STRING).description(
                                                       "장소 설명"),
                                               fieldWithPath("placeList[].likeCount").type(JsonFieldType.NUMBER).description("좋아요 숫자"),
                                               fieldWithPath("placeList[]isBookMarked").type(JsonFieldType.BOOLEAN).description("북마크 여부 확인"),
                                               fieldWithPath("placeList[]isLiked").type(JsonFieldType.BOOLEAN).description("좋아요 여부 확인"),
                                               fieldWithPath("placeList[].kakaoId").type(JsonFieldType.NUMBER).description("카카오맵 식별자"),
                                               fieldWithPath("placeList[].category").type(JsonFieldType.STRING).description(
                                                       "카테고리"),
                                               fieldWithPath("placeList[].latitude").type(JsonFieldType.STRING).description("위도"),
                                               fieldWithPath("placeList[].longitude").type(JsonFieldType.STRING).description("경도"),
                                               fieldWithPath("placeList[].createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                               fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수"),
                                               fieldWithPath("presentPage").type(JsonFieldType.NUMBER).description("현재 페이지 수"),
                                               fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("등록된 장소 수")
                                       )
                               )));

    }
}
