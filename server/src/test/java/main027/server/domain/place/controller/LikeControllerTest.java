package main027.server.domain.place.controller;


import com.google.gson.Gson;
import main027.server.domain.place.dto.LikeDto;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.mapper.PlaceLikeUserMapper;
import main027.server.domain.place.mapper.PlaceMapper;
import main027.server.domain.place.service.PlaceLikeService;
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

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = LikeController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,// ??????
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {SecurityConfig.class,
                        InterceptorConfig.class})}
)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class LikeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @MockBean
    private PlaceLikeService placeLikeService;
    @MockBean
    private PlaceLikeUserMapper placeLikeUserMapper;
    @MockBean
    private PlaceMapper placeMapper;
    @MockBean
    private DataHolder dataHolder;

    @Test
    void postLikeTest() throws Exception {
        //given
        Long id = 1L;

        Boolean isLiked = true;
        int likeCount = 10;

        LikeDto.Response response = new LikeDto.Response(isLiked, likeCount);
        given(placeLikeService.changeLikeUserStatus(Mockito.any())).willReturn(response);

        //when
        ResultActions actions =
                mockMvc.perform(post("/likes/{placeId}", id)
                                        .accept(MediaType.APPLICATION_JSON_UTF8)
                );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(content().string("{\"isLiked\":true,\"likeCount\":10}"))
                .andDo(document("post-like",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        List.of(
                                                fieldWithPath("isLiked").type(JsonFieldType.BOOLEAN).description("????????? ?????? ??????"),
                                                fieldWithPath("likeCount").type(JsonFieldType.NUMBER).description("????????? ?????? ??????")
                                        )
                                )));

    }

    @Test
    void getLikedListTest() throws Exception{
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

        Page<Place> placeLikePage = new PageImpl<>(List.of());

        given(placeLikeService.findPlaceMemberLiked(Mockito.anyLong(), eq(Pageable.ofSize(10)))).willReturn(placeLikePage);
        given(placeMapper.pageToList(Mockito.any(),Mockito.anyLong())).willReturn(pageResponseDto);

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/likes")
                                .param("page", "1")
                                .param("size", "10")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document("get-placeLikedList",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestParameters(
                                        parameterWithName("page").description("?????????"),
                                        parameterWithName("size").description("????????? ??????")
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
}
