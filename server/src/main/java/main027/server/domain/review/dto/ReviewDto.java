package main027.server.domain.review.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        @NotNull
        private Long userId;

        @NotNull
        @Length(max = 40)
        private String content;

        @NotNull
        private Long emojiId;

    }

    @Getter
    @Setter
    public static class Response {
        private Long reviewId;
        private Long userId;
        private Long placeId;
        private String content;
        private Long emojiId;
        private LocalDateTime createdAt;
    }
}
