package main027.server.domain.review.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ReviewDto {

    @Getter
    @Setter
    public static class Post {
        @NotNull
        private String userId;

        @NotNull
        @Max(40)
        private String content;

        @NotNull
        @Max(20)
        private String emoji;

    }

    @Getter
    @Setter
    public static class Response {
        private String id;
        private String userId;
        private String placeId;
        private String content;
        private String emoji;
        private LocalDateTime createdAt;
    }
}
