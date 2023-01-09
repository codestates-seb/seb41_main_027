package main027.server.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class MemberDto {
    @AllArgsConstructor
    public static class Post {

        @Email
        @NotBlank
        private String email;

        @NotBlank
        @Size(min = 2, max = 12)
        @Pattern(regexp = "^[a-zA-Zㄱ-힣]+$",
                message = "닉네임은 최소 2글자 이상 12글자 이하여야 합니다. 또, 특수문자 및 공백은 포함될 수 없습니다.")
        private String nickName;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        @Setter
        private Long id;
        @NotBlank
        @Size(min = 2, max = 12)
        @Pattern(regexp = "^[a-zA-Zㄱ-힣]+$",
                message = "닉네임은 최소 2글자 이상 12글자 이하여야 합니다. 또, 특수문자 및 공백은 포함될 수 없습니다.")
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
