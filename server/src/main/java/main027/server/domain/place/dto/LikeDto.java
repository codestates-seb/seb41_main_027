package main027.server.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class LikeDto {

    @AllArgsConstructor
    @Getter
    public static class Response {
        private Boolean isLiked;
        private int likeCount;
    }

}
