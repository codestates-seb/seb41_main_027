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

import static org.mockito.ArgumentMatchers.anyLong;
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
        excludeAutoConfiguration = SecurityAutoConfiguration.class,// ??????
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {SecurityConfig.class,
                        InterceptorConfig.class})}
)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class PlaceControllerTest {

    /**
     * ?????? ??????????????? ????????? MockBean?????? ????????? ??????????????? ???????????????,
     *      ?????? @WebMvcTest ?????????????????? ????????? Bean??? ????????????, @SpringBootTest??? ?????? Bean??? ???????????? ????????????.
     *      (????????? @SpringBootTest??? ????????? ????????? ?????? ?????????.)
     *
     *      ????????? ????????? ???????????? ?????? @MockBean??? ????????????????????????,
     *      org.springframework.beans.factory.UnsatisfiedDependencyException
     *      nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException
     *      ?????? ????????? ???????????? ??????.
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
        PlaceDto.PlacePostDto placePostDto = new PlaceDto.PlacePostDto("?????????7?????????????????????",
                                                                       "????????? ?????????",
                                                                       "??????????????????",
                                                                       0L,
                                                                       1L,
                                                                       "37",
                                                                       "37");

        String content = gson.toJson(placePostDto);

        PlaceDto.PlaceResponseDto responseDto = new PlaceDto.PlaceResponseDto(1L,
                                                                              1L,
                                                                              "?????????7?????????????????????",
                                                                              "????????? ?????????",
                                                                              "??????????????????",
                                                                              3,
                                                                              true,
                                                                              true,
                                                                              0L,
                                                                              "??????",
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
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("address").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("description").type(JsonFieldType.STRING).description(
                                                        "?????? ??????"),
                                                fieldWithPath("kakaoId").type(JsonFieldType.NUMBER).description("???????????? ?????????"),
                                                fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description(
                                                        "???????????? ?????????"),
                                                fieldWithPath("latitude").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("longitude").type(JsonFieldType.STRING).description("??????")
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("placeId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("address").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("description").type(JsonFieldType.STRING).description(
                                                        "?????? ??????"),
                                                fieldWithPath("likeCount").type(JsonFieldType.NUMBER).description("????????? ??????"),
                                                fieldWithPath("isBookMarked").type(JsonFieldType.BOOLEAN).description("????????? ?????? ??????"),
                                                fieldWithPath("isLiked").type(JsonFieldType.BOOLEAN).description("????????? ?????? ??????"),
                                                fieldWithPath("kakaoId").type(JsonFieldType.NUMBER).description("???????????? ?????????"),
                                                fieldWithPath("category").type(JsonFieldType.STRING).description(
                                                        "????????????"),
                                                fieldWithPath("latitude").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("longitude").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("?????? ??????")
                                        )
                                )));
    }

    @Test
    void patchPlaceTest() throws Exception {
        // given
        Long id = 1L;
        PlaceDto.PlacePatchDto placePatchDto = new PlaceDto.PlacePatchDto(1L,
                                                                          "?????? ????????????");

        String content = gson.toJson(placePatchDto);

        PlaceDto.PlaceResponseDto responseDto = new PlaceDto.PlaceResponseDto(1L,
                                                                              1L,
                                                                              "?????????7?????????????????????",
                                                                              "????????? ?????????",
                                                                              "?????? ????????????",
                                                                              3,
                                                                              true,
                                                                              true,
                                                                              0L,
                                                                              "??????",
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
                                                fieldWithPath("placeId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("description").type(JsonFieldType.STRING).description("?????? ??????")
                                        )),
                                responseFields(
                                        List.of(
                                                fieldWithPath("placeId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("address").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("description").type(JsonFieldType.STRING).description(
                                                        "?????? ??????"),
                                                fieldWithPath("likeCount").type(JsonFieldType.NUMBER).description("????????? ??????"),
                                                fieldWithPath("isBookMarked").type(JsonFieldType.BOOLEAN).description("????????? ?????? ??????"),
                                                fieldWithPath("isLiked").type(JsonFieldType.BOOLEAN).description("????????? ?????? ??????"),
                                                fieldWithPath("kakaoId").type(JsonFieldType.NUMBER).description("???????????? ?????????"),
                                                fieldWithPath("category").type(JsonFieldType.STRING).description(
                                                        "????????????"),
                                                fieldWithPath("latitude").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("longitude").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("?????? ??????")
                                        )
                                )));
    }

    @Test
    void searchPlaces() throws Exception{
        //given
        String Keyword = "????????????";
        List<PlaceDto.SearchResponseDto> searchResponseDtos = List.of(
                new PlaceDto.SearchResponseDto(1L,
                                               "?????????7?????????????????????",
                                               "????????? ?????????",
                                               "??????",
                                               "37",
                                               "127",
                                               "?????? ????????????",
                                               10),
                new PlaceDto.SearchResponseDto(2L,
                                               "?????????7?????????????????????",
                                               "????????? ?????????",
                                               "??????",
                                               "37",
                                               "127",
                                               "?????? ????????????",
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
                                .param("keyword", "????????????")
                                .param("page", "1")
                                .accept(MediaType.APPLICATION_JSON_UTF8));

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document("search-places",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestParameters(
                                        parameterWithName("keyword").description("?????????"),
                                        parameterWithName("page").description("?????? ?????????")
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("placeList").type(JsonFieldType.ARRAY).description("?????? ?????????").optional(),
                                                fieldWithPath("placeList[].placeId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("placeList[].name").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("placeList[].category").type(JsonFieldType.STRING).description(
                                                        "????????????"),
                                                fieldWithPath("placeList[].latitude").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("placeList[].longitude").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("placeList[].address").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("placeList[].description").type(JsonFieldType.STRING).description(
                                                        "?????? ??????"),
                                                fieldWithPath("placeList[].likeCount").type(JsonFieldType.NUMBER).description("????????? ??????"),
                                                fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("?????? ????????? ???"),
                                                fieldWithPath("presentPage").type(JsonFieldType.NUMBER).description("?????? ????????? ???"),
                                                fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("????????? ?????? ???")
                                        )
                                )));
    }

    @Test
    void getPlace() throws Exception {
        Long placeId = 1L;
        PlaceDto.PlaceResponseDto responseDto = new PlaceDto.PlaceResponseDto(1L,
                                                                              1L,
                                                                              "?????????7?????????????????????",
                                                                              "????????? ?????????",
                                                                              "??????????????????",
                                                                              3,
                                                                              true,
                                                                              true,
                                                                              0L,
                                                                              "??????",
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
                                                fieldWithPath("placeId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("address").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("description").type(JsonFieldType.STRING).description(
                                                        "?????? ??????"),
                                                fieldWithPath("likeCount").type(JsonFieldType.NUMBER).description("????????? ??????"),
                                                fieldWithPath("isBookMarked").type(JsonFieldType.BOOLEAN).description("????????? ?????? ??????"),
                                                fieldWithPath("isLiked").type(JsonFieldType.BOOLEAN).description("????????? ?????? ??????"),
                                                fieldWithPath("kakaoId").type(JsonFieldType.NUMBER).description("???????????? ?????????"),
                                                fieldWithPath("category").type(JsonFieldType.STRING).description(
                                                        "????????????"),
                                                fieldWithPath("latitude").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("longitude").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("?????? ??????")
                                        )
                                )));
    }



    @Test
    void getPlaces() throws Exception {
        //given

        List<PlaceDto.PlaceResponseDto> responseDtos = List.of(
                new PlaceDto.PlaceResponseDto(1L,
                                              1L,
                                              "?????????7?????????????????????",
                                              "????????? ?????????",
                                              "?????? ????????????",
                                              3,
                                              true,
                                              true,
                                              0L,
                                              "??????",
                                              "37",
                                              "127",
                                              LocalDateTime.of(2023,01,19,13,20)),

                new PlaceDto.PlaceResponseDto(2L,
                                              1L,
                                              "?????????2?????????????????????",
                                              "????????? ?????????",
                                              "?????? ????????????",
                                              4,
                                              true,
                                              true,
                                              0L,
                                              "??????",
                                              "37",
                                              "127",
                                              LocalDateTime.of(2023,01,20,12,30)),
                new PlaceDto.PlaceResponseDto(3L,
                                              2L,
                                              "?????? ????????????",
                                              "??????????????? ????????? ????????? ????????????24??? 25-4",
                                              "??? ??? ????????? ?????? ??? ?????? ??? ???!!",
                                              999,
                                              false,
                                              false,
                                              0L,
                                              "??????",
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
                                        parameterWithName("page").description("?????? ?????????"),
                                        parameterWithName("categoryId").description("???????????? ?????????"),
                                        parameterWithName("sortBy").description("?????? ??????")
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("placeList").type(JsonFieldType.ARRAY).description("?????? ?????????").optional(),
                                                fieldWithPath("placeList[].placeId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("placeList[].memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                                fieldWithPath("placeList[].name").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("placeList[].address").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("placeList[].description").type(JsonFieldType.STRING).description(
                                                        "?????? ??????"),
                                                fieldWithPath("placeList[].likeCount").type(JsonFieldType.NUMBER).description("????????? ??????"),
                                                fieldWithPath("placeList[].isBookMarked").type(JsonFieldType.BOOLEAN).description("????????? ?????? ??????"),
                                                fieldWithPath("placeList[].isLiked").type(JsonFieldType.BOOLEAN).description("????????? ?????? ??????"),
                                                fieldWithPath("placeList[].kakaoId").type(JsonFieldType.NUMBER).description("???????????? ?????????"),
                                                fieldWithPath("placeList[].category").type(JsonFieldType.STRING).description(
                                                        "????????????"),
                                                fieldWithPath("placeList[].latitude").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("placeList[].longitude").type(JsonFieldType.STRING).description("??????"),
                                                fieldWithPath("placeList[].createdAt").type(JsonFieldType.STRING).description("?????? ??????"),
                                                fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("?????? ????????? ???"),
                                                fieldWithPath("presentPage").type(JsonFieldType.NUMBER).description("?????? ????????? ???"),
                                                fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("????????? ?????? ???")
                                        )
                                )));
    }

    @Test
    void deletePlace() throws Exception {
        Long id = 1L;
        doNothing().when(placeService).deletePlace(Mockito.anyLong(),Mockito.anyList(), Mockito.anyLong());

        ResultActions actions =
                mockMvc.perform(delete("/places/{placeId}", id));
        actions
                .andExpect(status().isNoContent())
                .andDo(document("delete-place",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        parameterWithName("placeId").description("?????? ?????????")
                                )
                ));
    }
}