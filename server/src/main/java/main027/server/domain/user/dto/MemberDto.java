package main027.server.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberDto {
    public static class Post {
        private String email;
        private String nickName;
    }

    @Getter
    public static class Patch {
        private String nickName;

        public Patch(String nickName) {
            this.nickName = nickName;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String email;
        private String nickName;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
