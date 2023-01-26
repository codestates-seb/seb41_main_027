package main027.server.global.advice.exception;

import lombok.Getter;

public class TokenException extends RuntimeException{
    @Getter
    private ExceptionCode exceptionCode;

    public TokenException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
