package main027.server.domain.place;

import com.google.gson.Gson;

import main027.server.domain.place.dto.PlaceDto;
import main027.server.domain.place.entity.Place;
import main027.server.domain.place.mapper.PlaceMapper;
import main027.server.domain.place.service.PlaceService;
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
    private PlaceMapper placeMapper;

    @Autowired
    private Gson gson;

    @Test
    void postPlaceTest() throws Exception {
        //given
        PlaceDto.PlacePostDto placePostDto = new PlaceDto.PlacePostDto(1L,"강남역7번출구맥도날드","서울시 강남구","사람이많아요");

        String content = gson.toJson(placePostDto);

//        PlaceDto.PlaceResponseDto responseDto = new PlaceDto.PlaceResponseDto(1L,
//                                                                              1L,
//                                                                              "강남역7번출구맥도날드",
//                                                                              "서울시 강남구",
//                                                                              "사람이많아요",
//                                                                              "햄버거",
//                                                                              0L,
//                                                                              37L,
//                                                                              127L);
//
//        given(placeMapper.placePostDtoToPlace(Mockito.any(PlaceDto.PlacePostDto.class))).willReturn(new Place());
//        given(placeService.createPlace(Mockito.any(Place.class))).willReturn(new Place());
//        given(placeMapper.placeToPlaceResponseDto(Mockito.any(Place.class))).willReturn(responseDto);

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
        long id = 1L;
        PlaceDto.PlacePatchDto placePatchDto = new PlaceDto.PlacePatchDto( 1L,"존나 맛없어요");

        String content = gson.toJson(placePatchDto);

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
        PlaceDto.PlacePostDto placePostDto = new PlaceDto.PlacePostDto(1L, "강남역맥도날드","서울시강남구","존나 맛없어요");
        String contnet = gson.toJson(placePostDto);

        ResultActions actions =
                mockMvc.perform(
                        post("/places")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(contnet)
                );
        mockMvc.perform(
                get("/places/{}placeId")
                        .accept(MediaType.APPLICATION_JSON)
        )
               .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(placePostDto.getName()));

    }

    @Test
    void deletePlace() throws Exception {
        long id = 1L;

        ResultActions actions =
                mockMvc.perform(delete("/places/{placeId}", id));
        actions
                .andExpect(status().isNoContent());

    }
}