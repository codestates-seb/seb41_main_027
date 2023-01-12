package main027.server.domain.bookmark.controller;

import com.google.gson.Gson;
import main027.server.domain.bookmark.dto.BookmarkDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("stub")
@Transactional
public class BookmarkControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    /**
     * Bookmark Post 컨트롤러로 요청이 들어갔을 때 북마크를 저장했는지 확인하는 통합 테스트
     * 성공적으로 저장이 됬다면 true를 반환
     */
    @Test
    public void postBookmark() throws Exception {
        //given
        BookmarkDto.Post postDto = new BookmarkDto.Post(1L, 1L);
        String content = gson.toJson(postDto);

        //when
        ResultActions actions = mockMvc.perform(
                post("/bookmarks")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions.andExpect(status().isOk())
               .andExpect(content().string("true"));
    }

    @Test
    void getBookmarkList() throws Exception {
        //given
        addBookmark();
        String memberId = "/1?page=";
        String page = "1";

        //when
        ResultActions actions = mockMvc.perform(
                get("/bookmarks/" + memberId + page)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        actions.andExpect(status().isOk())
               .andExpect(jsonPath("$.presentPage").value(1))
               .andExpect(jsonPath("$.placeList").exists());

    }

    private void addBookmark() throws Exception {
        BookmarkDto.Post postDto = new BookmarkDto.Post(1L, 1L);
        String content = gson.toJson(postDto);

        mockMvc.perform(
                post("/bookmarks")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );
    }

}
