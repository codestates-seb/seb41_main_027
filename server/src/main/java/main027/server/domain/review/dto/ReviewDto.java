package main027.server.domain.review.dto;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class ReviewDto {

    @Getter
    @Setter
    public static class Post {
        @NotNull
        private Long userId;

        @NotNull
        @Length(max = 40)
        private String content;

        @NotNull
        @Length(max = 20)
        private String emoji;

    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private Long userId;
        private Long placeId;
        private String content;
        private String emoji;
        private LocalDateTime createdAt;
    }
}
