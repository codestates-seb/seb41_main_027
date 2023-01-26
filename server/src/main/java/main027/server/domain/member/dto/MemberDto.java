package main027.server.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @Email
        @NotBlank(message = "이메일은 공백일 수 없습니다.")
        private String email;

        @NotBlank
        @Size(min = 2, max = 12)
        @Pattern(regexp = "^[a-zA-Zㄱ-힣0-9]+$",
                message = "닉네임은 최소 2글자 이상 12글자 이하여야 합니다. 또, 특수문자 및 공백은 포함될 수 없습니다.")
        private String nickName;

        @NotBlank
        @Size(min = 8, max = 16, message = "비밀번호는 최소 8자 이상, 최대 16자 이하여야 합니다.")
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        @Size(min = 2, max = 12)
        @Pattern(regexp = "^[a-zA-Zㄱ-힣0-9]+$",
                message = "닉네임은 최소 2글자 이상 12글자 이하여야 합니다. 또, 특수문자 및 공백은 포함될 수 없습니다.")
        private String nickName;

        @Size(min = 8, max = 16, message = "비밀번호는 최소 8자 이상, 최대 16자 이하여야 합니다.")
        private String password;

        public Patch(String nickName) {
            this.nickName = nickName;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long memberId;
        private String email;
        private String nickName;
        public List<String> roles;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
