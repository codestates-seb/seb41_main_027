package main027.server.domain.review.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReviewDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {

        @NotNull
        private Long placeId;

        @NotNull
        @Length(max = 40)
        private String content;

        @NotNull
        private Long emojiId;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long reviewId;
        private Long memberId;
        private Long placeId;
        private Long emojiId;
        private String content;
        private LocalDateTime createdAt;
    }

    /**
     * Place에 저장된 Review 리스트를 제공하기 위한 ResponseDto
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListResponse {
        private List<Response> reviewList = new ArrayList<>();
        /** 페이지의 전체 페이지 수 */
        private Long totalPages;
        /** 현재 페이지 */
        private Long presentPage;
        /** Review의 총 개수 */
        private Long totalElements;
    }

}
