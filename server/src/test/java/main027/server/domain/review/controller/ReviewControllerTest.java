package main027.server.domain.review.controller;

import com.google.gson.Gson;
import main027.server.domain.review.dto.ReviewDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "/application-test.properties")
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @Test
    public void postReview() throws Exception {
        //given
        ReviewDto.Post post = new ReviewDto.Post(1L, 1L, "맛있어요", 1L);

        String content = gson.toJson(post);

        //when
        ResultActions actions = mockMvc.perform(
                post("/reviews")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        // then
        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.reviewId").isNumber())
                .andExpect(jsonPath("$.memberId").value(1L))
                .andExpect(jsonPath("$.placeId").value(1L))
                .andExpect(jsonPath("$.emojiId").value(1L))
                .andExpect(jsonPath("$.content").value(post.getContent()));
    }
}
