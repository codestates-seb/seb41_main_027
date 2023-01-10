package main027.server.global.exception;

import static org.springframework.http.HttpStatus.*;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(NOT_FOUND.value(), "Member Not Found"),
    MEMBER_ALREADY_EXISTS(CONFLICT.value(), "Member Already Exists"),
    PLACE_NOT_FOUND(NOT_FOUND.value(), "Place Not Found"),
    PLACE_ALREADY_EXISTS(CONFLICT.value(), "Place Already Exists"),
    EMOJI_NOT_FOUND(NOT_FOUND.value(), "Emoji Not Found"),
    REVIEW_NOT_FOUND(NOT_FOUND.value(), "Review Not Found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
