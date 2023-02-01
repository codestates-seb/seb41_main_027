package main027.server.domain.place.controller;

import com.google.gson.Gson;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.mapper.PlaceMapper;
import main027.server.domain.place.service.PlaceService;
import main027.server.domain.place.service.PlaceUpdateService;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = PlaceController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,// 추가
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {SecurityConfig.class,
                        InterceptorConfig.class})}
)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class PlaceControllerTest {

    /**
     * 실제 사용하지는 않지만 MockBean으로 생성한 클래스들이 존재하는데,
     *      이는 @WebMvcTest 어노테이션은 필요한 Bean만 가져오고, @SpringBootTest는 모든 Bean을 가져오기 때문이다.
     *      (당연히 @SpringBootTest가 테스트 속도가 훨씬 느리다.)
     *
     *      실제로 하단에 존재하는 일부 @MockBean을 주석처리하게되면,
     *      org.springframework.beans.factory.UnsatisfiedDependencyException
     *      nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException
     *      해당 예외가 발생하게 된다.
     */

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlaceService placeService;
    @MockBean
    private PlaceUpdateService placeUpdateService;
    @MockBean
    private PlaceMapper placeMapper;
    @MockBean
    private DataHolder dataHolder;
    @Autowired
    private Gson gson;

    @Test
    void postPlaceTest() throws Exception {
        //given
        PlaceDto.PlacePostDto placePostDto = new PlaceDto.PlacePostDto("강남역7번출구맥도날드",
                                                                       "서울시 강남구",
                                                                       "사람이많아요",
                                                                       0L,
                                                                       1L,
                                                                       "37",
                                                                       "37");

        String content = gson.toJson(placePostDto);

        PlaceDto.PlaceResponseDto responseDto = new PlaceDto.PlaceResponseDto(1L,
                                                                              1L,
                                                                              "강남역7번출구맥도날드",
                                                                              "서울시 강남구",
                                                                              "사람이많아요",
                                                                              3,
                                                                              true,
                                                                              true,
                                                                              0L,
                                                                              "푸드",
                                                                              "37",
                                                                              "37",
                                                                              LocalDateTime.now());

        given(placeMapper.placePostDtoToPlace(Mockito.any(),Mockito.anyLong())).willReturn(new Place());
        given(placeService.createPlace(Mockito.any(Place.class))).willReturn(new Place());
        given(placeMapper.placeToPlaceResponseDto(Mockito.any(Place.class),Mockito.anyLong())).willReturn(responseDto);

        //when
        ResultActions actions =
                mockMvc.perform(post("/places")
                                        .accept(MediaType.APPLICATION_JSON_UTF8)
                                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                                        .content(content)
                );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(placePostDto.getName()))
                .andExpect(jsonPath("$.address").value(placePostDto.getAddress()))
                .andExpect(jsonPath("$.description").value(placePostDto.getDescription()))
                .andDo(document("post-place",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        List.of(
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("장소 이름"),
                                                fieldWithPath("address").type(JsonFieldType.STRING).description("장소 주소"),
                                                fieldWithPath("description").type(JsonFieldType.STRING).description(
                                                        "장소 설명"),
                                                fieldWithPath("kakaoId").type(JsonFieldType.NUMBER).description("카카오맵 식별자"),
                                                fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description(
                                                        "카테고리 식별자"),
                                                fieldWithPath("latitude").type(JsonFieldType.STRING).description("위도"),
                                                fieldWithPath("longitude").type(JsonFieldType.STRING).description("경도")
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("placeId").type(JsonFieldType.NUMBER).description("장소 식별자"),
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("장소 이름"),
                                                fieldWithPath("address").type(JsonFieldType.STRING).description("장소 주소"),
                                                fieldWithPath("description").type(JsonFieldType.STRING).description(
                                                        "장소 설명"),
                                                fieldWithPath("likeCount").type(JsonFieldType.NUMBER).description("좋아요 숫자"),
                                                fieldWithPath("isBookMarked").type(JsonFieldType.BOOLEAN).description("북마크 여부 확인"),
                                                fieldWithPath("isLiked").type(JsonFieldType.BOOLEAN).description("좋아요 여부 확인"),
                                                fieldWithPath("kakaoId").type(JsonFieldType.NUMBER).description("카카오맵 식별자"),
                                                fieldWithPath("category").type(JsonFieldType.STRING).description(
                                                        "카테고리"),
                                                fieldWithPath("latitude").type(JsonFieldType.STRING).description("위도"),
                                                fieldWithPath("longitude").type(JsonFieldType.STRING).description("경도"),
                                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간")
                                        )
                                )));
    }

    @Test
    void patchPlaceTest() throws Exception {
        // given
        Long id = 1L;
        PlaceDto.PlacePatchDto placePatchDto = new PlaceDto.PlacePatchDto(1L,
                                                                          "존나 맛없어요");

        String content = gson.toJson(placePatchDto);

        PlaceDto.PlaceResponseDto responseDto = new PlaceDto.PlaceResponseDto(1L,
                                                                              1L,
                                                                              "강남역7번출구맥도날드",
                                                                              "서울시 강남구",
                                                                              "존나 맛없어요",
                                                                              3,
                                                                              true,
                                                                              true,
                                                                              0L,
                                                                              "공방",
                                                                              "37",
                                                                              "127",
                                                                              LocalDateTime.now());

        given(placeMapper.placePatchDtoToPlace(Mockito.any(PlaceDto.PlacePatchDto.class))).willReturn(new Place());
        given(placeUpdateService.updatePlace(Mockito.anyLong(), Mockito.any(Place.class))).willReturn(new Place());
        given(placeMapper.placeToPlaceResponseDto(Mockito.any(Place.class),Mockito.anyLong())).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(patch("/places/{placeId}", id)
                                        .accept(MediaType.APPLICATION_JSON_UTF8)
                                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                                        .content(content));
        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(placePatchDto.getDescription()))
                .andDo(document("patch-place",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        List.of(
                                                fieldWithPath("placeId").type(JsonFieldType.NUMBER).description("장소 식별자"),
                                                fieldWithPath("description").type(JsonFieldType.STRING).description("장소 주소")
                                        )),
                                responseFields(
                                        List.of(
                                                fieldWithPath("placeId").type(JsonFieldType.NUMBER).description("장소 식별자"),
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("장소 이름"),
                                                fieldWithPath("address").type(JsonFieldType.STRING).description("장소 주소"),
                                                fieldWithPath("description").type(JsonFieldType.STRING).description(
                                                        "장소 설명"),
                                                fieldWithPath("likeCount").type(JsonFieldType.NUMBER).description("좋아요 숫자"),
                                                fieldWithPath("isBookMarked").type(JsonFieldType.BOOLEAN).description("북마크 여부 확인"),
                                                fieldWithPath("isLiked").type(JsonFieldType.BOOLEAN).description("좋아요 여부 확인"),
                                                fieldWithPath("kakaoId").type(JsonFieldType.NUMBER).description("카카오맵 식별자"),
                                                fieldWithPath("category").type(JsonFieldType.STRING).description(
                                                        "카테고리"),
                                                fieldWithPath("latitude").type(JsonFieldType.STRING).description("위도"),
                                                fieldWithPath("longitude").type(JsonFieldType.STRING).description("경도"),
                                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간")
                                        )
                                )));
    }

    @Test
    void searchPlaces() throws Exception{
        //given
        String Keyword = "맥도날드";
        List<PlaceDto.SearchResponseDto> searchResponseDtos = List.of(
                new PlaceDto.SearchResponseDto(1L,
                                               "강남역7번출구맥도날드",
                                               "서울시 강남구",
                                               "푸드",
                                               "37",
                                               "127",
                                               "존나 맛없어요",
                                               10),
                new PlaceDto.SearchResponseDto(2L,
                                               "건대역7번출구맥도날드",
                                               "서울시 광진구",
                                               "푸드",
                                               "37",
                                               "127",
                                               "존나 맛없어요",
                                               3));

        PlaceDto.SearchPageResponseDto searchPageResponseDto = new PlaceDto.SearchPageResponseDto();
        searchPageResponseDto.setPlaceList(searchResponseDtos);
        searchPageResponseDto.setPresentPage(1L);
        searchPageResponseDto.setTotalPages(1L);
        searchPageResponseDto.setTotalElements(2L);

        Page<Place> placePage = new PageImpl<>(List.of());

        given(placeService.searchPlace(Pageable.ofSize(5), Keyword)).willReturn(placePage);
        given(placeMapper.searchPageToList(Mockito.any())).willReturn(searchPageResponseDto);

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/places/search")
                                .param("keyword", "맥도날드")
                                .param("page", "1")
                                .accept(MediaType.APPLICATION_JSON_UTF8));

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document("search-places",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestParameters(
                                        parameterWithName("keyword").description("검색어"),
                                        parameterWithName("page").description("현재 페이지")
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("placeList").type(JsonFieldType.ARRAY).description("결과 데이터").optional(),
                                                fieldWithPath("placeList[].placeId").type(JsonFieldType.NUMBER).description("장소 식별자"),
                                                fieldWithPath("placeList[].name").type(JsonFieldType.STRING).description("장소 이름"),
                                                fieldWithPath("placeList[].category").type(JsonFieldType.STRING).description(
                                                        "카테고리"),
                                                fieldWithPath("placeList[].latitude").type(JsonFieldType.STRING).description("위도"),
                                                fieldWithPath("placeList[].longitude").type(JsonFieldType.STRING).description("경도"),
                                                fieldWithPath("placeList[].address").type(JsonFieldType.STRING).description("장소 주소"),
                                                fieldWithPath("placeList[].description").type(JsonFieldType.STRING).description(
                                                        "장소 설명"),
                                                fieldWithPath("placeList[].likeCount").type(JsonFieldType.NUMBER).description("좋아요 숫자"),
                                                fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수"),
                                                fieldWithPath("presentPage").type(JsonFieldType.NUMBER).description("현재 페이지 수"),
                                                fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("등록된 장소 수")
                                        )
                                )));
    }

    @Test
    void getPlace() throws Exception {
        Long placeId = 1L;
        PlaceDto.PlaceResponseDto responseDto = new PlaceDto.PlaceResponseDto(1L,
                                                                              1L,
                                                                              "강남역7번출구맥도날드",
                                                                              "서울시 강남구",
                                                                              "사람이많아요",
                                                                              3,
                                                                              true,
                                                                              true,
                                                                              0L,
                                                                              "푸드",
                                                                              "37",
                                                                              "127",
                                                                              LocalDateTime.now());

        given(placeService.findPlace(Mockito.anyLong())).willReturn(new Place());
        given(placeMapper.placeToPlaceResponseDto(Mockito.any(Place.class),Mockito.anyLong())).willReturn(responseDto);

        ResultActions actions =
                mockMvc.perform(
                        get("/places/{placeId}", placeId)
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                );
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(responseDto.getName()))
                .andDo(document("get-place",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        List.of(
                                                fieldWithPath("placeId").type(JsonFieldType.NUMBER).description("장소 식별자"),
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("장소 이름"),
                                                fieldWithPath("address").type(JsonFieldType.STRING).description("장소 주소"),
                                                fieldWithPath("description").type(JsonFieldType.STRING).description(
                                                        "장소 설명"),
                                                fieldWithPath("likeCount").type(JsonFieldType.NUMBER).description("좋아요 숫자"),
                                                fieldWithPath("isBookMarked").type(JsonFieldType.BOOLEAN).description("북마크 여부 확인"),
                                                fieldWithPath("isLiked").type(JsonFieldType.BOOLEAN).description("좋아요 여부 확인"),
                                                fieldWithPath("kakaoId").type(JsonFieldType.NUMBER).description("카카오맵 식별자"),
                                                fieldWithPath("category").type(JsonFieldType.STRING).description(
                                                        "카테고리"),
                                                fieldWithPath("latitude").type(JsonFieldType.STRING).description("위도"),
                                                fieldWithPath("longitude").type(JsonFieldType.STRING).description("경도"),
                                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간")
                                        )
                                )));
    }



    @Test
    void getPlaces() throws Exception {
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
                                              LocalDateTime.of(2023,01,19,13,20)),

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

        given(placeService.findPlaces(Pageable.ofSize(10), 3L, "")).willReturn(placePage);
        given(placeMapper.pageToList(Mockito.any(),Mockito.anyLong())).willReturn(pageResponseDto);

        ResultActions actions =
                mockMvc.perform(
                        get("/places")
                                .param("page", "1")
                                .param("categoryId", "3")
                                .param("sortBy","")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                );

        actions
                .andExpect(status().isOk())
                .andDo(document("get-places",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestParameters(
                                        parameterWithName("page").description("현재 페이지"),
                                        parameterWithName("categoryId").description("카테고리 식별자"),
                                        parameterWithName("sortBy").description("정렬 기준")
                                ),
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
    void deletePlace() throws Exception {
        Long id = 1L;
        doNothing().when(placeService).deletePlace(Mockito.anyLong(),Mockito.anyLong());

        ResultActions actions =
                mockMvc.perform(delete("/places/{placeId}", id));
        actions
                .andExpect(status().isNoContent())
                .andDo(document("delete-place",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        parameterWithName("placeId").description("장소 식별자")
                                )
                ));
    }
}