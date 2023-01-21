package main027.server.global.advice;

import lombok.extern.slf4j.Slf4j;
import main027.server.global.advice.dto.ErrorResponse;
import main027.server.global.advice.exception.BusinessLogicException;
import main027.server.global.advice.exception.PermissionDeniedException;
import main027.server.global.advice.exception.TokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {
    /**
     * 사용자가 정의한 예외가 발생한 경우
     * @param e
     * @return ErrorResponse 객체와 HTTP Status를 포함하고 있는 ResponseEntity 객체
     */
    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {
        final ErrorResponse response = ErrorResponse.of(e.getExceptionCode());
        return new ResponseEntity(response, HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }

    /**
     * 권한이 없는 사용자가 place를 수정, 삭제 review를 삭제 하려고 할 경우
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResponseEntity handlePermissionDeniedException(PermissionDeniedException e) {
        final ErrorResponse response = ErrorResponse.of(e.getExceptionCode());
        return new ResponseEntity(response, HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }

    @ExceptionHandler
    public ResponseEntity handleTokenException(TokenException e) {
        final ErrorResponse response = ErrorResponse.of(e.getExceptionCode());
        return new ResponseEntity(response, HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }

    /**
     * requestBody를 통해 전달받은 json 데이터를 dto 객체와 바인딩(dto 유효성 검증)에 실패한 경우
     * @param e
     * @return ErrorResponse 객체
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());
        return response;
    }

    /**
     *  Pathvariable로 받은 데이터가 유효성 검증에 실패한 경우
     * @param e
     * @return ErrorResponse 객체
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        final ErrorResponse response = ErrorResponse.of(e.getConstraintViolations());
        return response;
    }

    /**
     * 클라이언트 측에서 적절하지 않은 HTTP Method를 통해 request를 전송하는 경우
     * @param e
     * @return ErrorResponse 객체
     */

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        final ErrorResponse response = ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED);
        return response;
    }

    /**
     * 개발자의 구현 오류 등으로 인해 에러가 발생하는 경우
     * @param e
     * @return ErrorResponse 객체
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        log.error("# handle exception", e);
        final ErrorResponse response = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
