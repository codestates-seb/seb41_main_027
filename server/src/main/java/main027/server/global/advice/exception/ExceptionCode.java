package main027.server.global.advice.exception;

import lombok.Getter;

import static org.springframework.http.HttpStatus.*;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(NOT_FOUND.value(), "Member Not Found"),
    EMAIL_ALREADY_EXISTS(CONFLICT.value(), "This Email Already Exists"),
    NICKNAME_ALREADY_EXISTS(CONFLICT.value(), "This NickName Already Exists"),
    EMAIL_NICKNAME_ALREADY_EXISTS(CONFLICT.value(), "Email and NickName Already Exists"),
    PLACE_NOT_FOUND(NOT_FOUND.value(), "Place Not Found"),
    PLACE_ALREADY_EXISTS(CONFLICT.value(), "Place Already Exists"),
    EMOJI_NOT_FOUND(NOT_FOUND.value(), "Emoji Not Found"),
    REVIEW_NOT_FOUND(NOT_FOUND.value(), "Review Not Found"),
    INVALID_REFRESH_TOKEN(UNAUTHORIZED.value(), "Invalid Refresh Token"),
    INVALID_ACCESS_TOKEN(UNAUTHORIZED.value(), "Invalid Access Token"),
    PERMISSION_DENIED(FORBIDDEN.value(), "Permission Denied"),
    LOGOUT_MEMBER(UNAUTHORIZED.value(), "Logout Member"),
    UNREGISTERED_MEMBER(NOT_FOUND.value(), "Unregistered Member"),
    SHORT_KEYWORD(BAD_REQUEST.value(), "검색어를 2글자 이상 입력하세요.");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
