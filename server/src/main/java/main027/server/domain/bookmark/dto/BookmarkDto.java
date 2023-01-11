package main027.server.domain.bookmark.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookmarkDto {

    @NoArgsConstructor
    @Getter
    public static class Post {

        @NotNull
        private Long memberId;
        @NotNull
        private Long placeId;
    }

    public static class Response {

        private Boolean isBookmarked;
    }
}
