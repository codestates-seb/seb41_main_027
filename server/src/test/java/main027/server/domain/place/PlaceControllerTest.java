package main027.server.domain.place;

import com.google.gson.Gson;
import main027.server.domain.place.controller.PlaceController;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.mapper.PlaceMapper;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static main027.server.util.ApiDocumentUtils.getRequestPreProcessor;
import static main027.server.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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
    private PlaceMapper placeMapper;
    @Autowired
    private Gson gson;

    @Test
    void postPlaceTest() throws Exception {
        //given
        PlaceDto.PlacePostDto placePostDto = new PlaceDto.PlacePostDto(1L, "강남역7번출구맥도날드", "서울시 강남구", "사람이많아요", 0L, 1L
                , "37", "37");

        String content = gson.toJson(placePostDto);

        PlaceDto.PlaceResponseDto responseDto = new PlaceDto.PlaceResponseDto(1L,
                                                                              1L,
                                                                              "강남역7번출구맥도날드",
                                                                              "서울시 강남구",
                                                                              "사람이많아요",
                                                                              0L,
                                                                              "공방",
                                                                              "37",
                                                                              "37");

        given(placeMapper.placePostDtoToPlace(Mockito.any(PlaceDto.PlacePostDto.class))).willReturn(new Place());
        given(placeService.createPlace(Mockito.any(Place.class))).willReturn(new Place());
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
//                                getRequestPreProcessor(),
//                                getResponsePreProcessor(),

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
                                                fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리 식별자"),
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
                                                fieldWithPath("kakaoId").type(JsonFieldType.NUMBER).description("카카오맵 식별자"),
                                                fieldWithPath("category").type(JsonFieldType.STRING).description("카테고리"),
                                                fieldWithPath("latitude").type(JsonFieldType.STRING).description("위도"),
                                                fieldWithPath("longitude").type(JsonFieldType.STRING).description("경도")
                                        )
                                )));
    }

    @Test
    void patchPlaceTest() throws Exception {
        // given
        Long id = 1L;
        PlaceDto.PlacePatchDto placePatchDto = new PlaceDto.PlacePatchDto(1L, "존나 맛없어요");

        String content = gson.toJson(placePatchDto);

        PlaceDto.PlaceResponseDto responseDto = new PlaceDto.PlaceResponseDto(1L,
                                                                              1L,
                                                                              "강남역7번출구맥도날드",
                                                                              "서울시 강남구",
                                                                              "존나 맛없어요",
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
                                        .accept(MediaType.APPLICATION_JSON)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(content));
        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(placePatchDto.getDescription()));
    }

    @Test
    void getPlace() throws Exception {
        Long placeId = 1L;
        PlaceDto.PlaceResponseDto responseDto = new PlaceDto.PlaceResponseDto(1L,
                                                                              1L,
                                                                              "강남역7번출구맥도날드",
                                                                              "서울시 강남구",
                                                                              "사람이많아요",
                                                                              0L,
                                                                              "공방",
                                                                              "37",
                                                                              "127");
        given(placeService.findPlace(Mockito.anyLong())).willReturn(new Place());
        given(placeMapper.placeToPlaceResponseDto(Mockito.any(Place.class))).willReturn(responseDto);

        ResultActions actions =
                mockMvc.perform(
                        get("/places/{placeId}", placeId)
                                .accept(MediaType.APPLICATION_JSON)
                );
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(responseDto.getName()));

    }

//    @Test
//    void getPlaces() throws Exception {
//
//        List<PlaceDto.PlaceResponseDto> responseDto = List.of(
//                new PlaceDto.PlaceResponseDto(1L,
//                                              1L,
//                                              "강남역7번출구맥도날드",
//                                              "서울시 강남구",
//                                              "존나 맛없어요",
//                                              0L,
//                                              "햄버거",
//                                              37L,
//                                              127L),
//
//                new PlaceDto.PlaceResponseDto(2L,
//                                              1L,
//                                              "건대역2번출구맥도날드",
//                                              "서울시 광진구",
//                                              "존나 맛있어요",
//                                              0L,
//                                              "햄버거",
//                                              37L,
//                                              127L));
//
//        Page<Place> placePage = new PageImpl<>(List.of());
//
//        given(placeService.findPlaces(Mockito.any(Pageable.class))).willReturn(placePage);
//        given()
//
//        ResultActions actions =
//                mockMvc.perform(
//                        get("/places")
//                                .param("page", "1")
//                                .param("size", "10")
//                                .accept(MediaType.APPLICATION_JSON)
//                );
//
//        actions
//                .andExpect(status().isOk());
//    }

    @Test
    void deletePlace() throws Exception {
        Long id = 1L;

        ResultActions actions =
                mockMvc.perform(delete("/places/{placeId}", id));
        actions
                .andExpect(status().isNoContent());

    }
}