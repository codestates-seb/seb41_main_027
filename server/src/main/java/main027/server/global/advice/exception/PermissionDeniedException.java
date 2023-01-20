package main027.server.global.advice.exception;

import lombok.Getter;

public class PermissionDeniedException extends RuntimeException{
    @Getter
    private ExceptionCode exceptionCode;

    public PermissionDeniedException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
