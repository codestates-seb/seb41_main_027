package main027.server.domain.bookmark.dto;

import javax.validation.constraints.NotBlank;

public class BookmarkDto {

    public static class Post {

        @NotBlank
        private Long memberId;
        @NotBlank
        private Long placeId;
    }

    public static class Response {

        private Boolean isBookmarked;
    }
}
