package main027.server.domain.place;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import main027.server.domain.member.entity.Member;
import main027.server.domain.place.controller.PlaceController;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Category;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.mapper.PlaceLikeUserMapper;
import main027.server.domain.place.mapper.PlaceMapper;
import main027.server.domain.place.service.PlaceLikeService;
import main027.server.domain.place.service.PlaceService;
import main027.server.domain.place.service.PlaceUpdateService;
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
import org.springframework.data.domain.*;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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
        excludeAutoConfiguration = SecurityAutoConfiguration.class, // 추가
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class PlaceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlaceService placeService;
    @MockBean
    private PlaceUpdateService placeUpdateService;
    @MockBean
    private PlaceLikeService placeLikeService;
    @MockBean
    PlaceLikeUserMapper placeLikeUserMapper;
    @MockBean
    private PlaceMapper placeMapper;
    @Autowired
    private Gson gson;

    @Test
    void postPlaceTest() throws Exception {
        //given
        PlaceDto.PlacePostDto placePostDto = new PlaceDto.PlacePostDto(1L,
                                                                       "강남역7번출구맥도날드",
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
                                                                              0L,
                                                                              "공방",
                                                                              "37",
                                                                              "37");

        given(placeMapper.placePostDtoToPlace(Mockito.any(PlaceDto.PlacePostDto.class))).willReturn(new Place());
        given(placeService.createPlace(Mockito.any(Place.class))).willReturn(new Place());
        given(placeLikeService.changeLikeUserStatus(Mockito.any())).willReturn(true);
        given(placeMapper.placeToPlaceResponseDto(Mockito.any(Place.class))).willReturn(responseDto);

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
                .andExpect(jsonPath("$.memberId").value(placePostDto.getMemberId()))
                .andExpect(jsonPath("$.name").value(placePostDto.getName()))
                .andExpect(jsonPath("$.address").value(placePostDto.getAddress()))
                .andExpect(jsonPath("$.description").value(placePostDto.getDescription()))
                .andDo(document("post-place",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        List.of(
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
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
                                                fieldWithPath("kakaoId").type(JsonFieldType.NUMBER).description("카카오맵 식별자"),
                                                fieldWithPath("category").type(JsonFieldType.STRING).description(
                                                        "카테고리"),
                                                fieldWithPath("latitude").type(JsonFieldType.STRING).description("위도"),
                                                fieldWithPath("longitude").type(JsonFieldType.STRING).description("경도")
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
                                                                              0L,
                                                                              "공방",
                                                                              "37",
                                                                              "127");

        given(placeMapper.placePatchDtoToPlace(Mockito.any(PlaceDto.PlacePatchDto.class))).willReturn(new Place());
        given(placeUpdateService.updatePlace(Mockito.any(Place.class))).willReturn(new Place());
        given(placeMapper.placeToPlaceResponseDto(Mockito.any(Place.class))).willReturn(responseDto);

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
                                                fieldWithPath("kakaoId").type(JsonFieldType.NUMBER).description("카카오맵 식별자"),
                                                fieldWithPath("category").type(JsonFieldType.STRING).description(
                                                        "카테고리"),
                                                fieldWithPath("latitude").type(JsonFieldType.STRING).description("위도"),
                                                fieldWithPath("longitude").type(JsonFieldType.STRING).description("경도")
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
                                                                              0L,
                                                                              "공방",
                                                                              "37",
                                                                              "127");
        given(placeService.findPlace(Mockito.anyLong())).willReturn(new Place());
        given(placeMapper.placeToPlaceResponseDto(Mockito.any(Place.class))).willReturn(responseDto);

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
                                                fieldWithPath("placeLikeUserList").type(JsonFieldType.ARRAY).description("좋아요 회원 리스트"),
                                                fieldWithPath("likeCount").type(JsonFieldType.NUMBER).description("좋아요 숫자"),
                                                fieldWithPath("kakaoId").type(JsonFieldType.NUMBER).description("카카오맵 식별자"),
                                                fieldWithPath("category").type(JsonFieldType.STRING).description(
                                                        "카테고리"),
                                                fieldWithPath("latitude").type(JsonFieldType.STRING).description("위도"),
                                                fieldWithPath("longitude").type(JsonFieldType.STRING).description("경도")
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
                                              0L,
                                              "햄버거",
                                              "37",
                                              "127"),

                new PlaceDto.PlaceResponseDto(2L,
                                              1L,
                                              "건대역2번출구맥도날드",
                                              "서울시 광진구",
                                              "존나 맛있어요",
                                              4,
                                              true,
                                              0L,
                                              "햄버거",
                                              "37",
                                              "127")

        );

        PlaceDto.PageResponseDto pageResponseDto = new PlaceDto.PageResponseDto();
        pageResponseDto.setPlaceList(responseDtos);
        pageResponseDto.setPresentPage(1L);
        pageResponseDto.setTotalPages(10L);
        pageResponseDto.setTotalElements(50L);




        Page<Place> placePage = new PageImpl<>(List.of());

        given(placeService.findPlaces(Pageable.ofSize(10))).willReturn(placePage);
        given(placeMapper.pageToList(Mockito.any())).willReturn(pageResponseDto);

        ResultActions actions =
                mockMvc.perform(
                        get("/places")
                                .param("page", "1")
                                .param("size", "10")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                );

        actions
                .andExpect(status().isOk())
                .andDo(document("get-places",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestParameters(
                                        parameterWithName("page").description("페이지"),
                                        parameterWithName("size").description("페이지 크기")
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
                                                fieldWithPath("placeList[]isBookMarked").type(JsonFieldType.BOOLEAN).description("북마크 여부 확인"),
                                                fieldWithPath("placeList[].kakaoId").type(JsonFieldType.NUMBER).description("카카오맵 식별자"),
                                                fieldWithPath("placeList[].category").type(JsonFieldType.STRING).description(
                                                        "카테고리"),
                                                fieldWithPath("placeList[].latitude").type(JsonFieldType.STRING).description("위도"),
                                                fieldWithPath("placeList[].longitude").type(JsonFieldType.STRING).description("경도"),
                                                fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수"),
                                                fieldWithPath("presentPage").type(JsonFieldType.NUMBER).description("현재 페이지 수"),
                                                fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("등록된 장소 수")
                                        )
                                )));
    }

    @Test
    void deletePlace() throws Exception {
        Long id = 1L;
        doNothing().when(placeService).deletePlace(Mockito.anyLong());

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