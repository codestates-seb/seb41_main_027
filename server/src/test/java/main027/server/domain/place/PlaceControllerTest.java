package main027.server.domain.place;

import com.google.gson.Gson;
import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.mapper.PlaceMapper;
import main027.server.domain.place.service.PlaceService;
import main027.server.domain.place.service.PlaceUpdateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
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
        PlaceDto.PlacePostDto placePostDto = new PlaceDto.PlacePostDto(1L, "강남역7번출구맥도날드", "서울시 강남구", "사람이많아요");

        String content = gson.toJson(placePostDto);

        PlaceDto.PlaceResponseDto responseDto = new PlaceDto.PlaceResponseDto(1L,
                                                                              1L,
                                                                              "강남역7번출구맥도날드",
                                                                              "서울시 강남구",
                                                                              "사람이많아요",
                                                                              0L,
                                                                              "공방",
                                                                              37L,
                                                                              127L);

        given(placeMapper.placePostDtoToPlace(Mockito.any(PlaceDto.PlacePostDto.class))).willReturn(new Place());
        given(placeService.createPlace(Mockito.any(Place.class))).willReturn(new Place());
        given(placeMapper.placeToPlaceResponseDto(Mockito.any(Place.class))).willReturn(responseDto);

        //when
        ResultActions actions =
                mockMvc.perform(post("/places")
                                        .accept(MediaType.APPLICATION_JSON)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(content)
                );

        //then
        actions
                .andExpect(status().isCreated());
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
                                                                              37L,
                                                                              127L);

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
                                                                              37L,
                                                                              127L);
        given(placeService.findPlace(Mockito.anyLong())).willReturn(new Place());
        given(placeMapper.placeToPlaceResponseDto(Mockito.any(Place.class))).willReturn(responseDto);

        ResultActions actions =
        mockMvc.perform(
                get("/places/{placeId}" , placeId)
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