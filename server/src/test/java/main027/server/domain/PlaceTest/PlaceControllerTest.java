package main027.server.domain.PlaceTest;

import com.google.gson.Gson;
import main027.server.domain.place.dto.PlaceDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlaceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    void postPlaceTest() throws Exception {
        //given
        PlaceDto.PlacePostDto placePostDto = new PlaceDto.PlacePostDto(1L,"강남역7번출구맥도날드","서울시 강남구","사람이많아요");

        String content = gson.toJson(placePostDto);

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
        PlaceDto.PlacePatchDto placePatchDto = new PlaceDto.PlacePatchDto( "존나 맛없어요");

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