package main027.server.global.auth.utils;

import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import main027.server.global.advice.dto.ErrorResponse;
import main027.server.global.advice.exception.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** ErrorResponse를 출력 스트림으로 생성 */
public class ErrorResponder {
    public static void sendErrorResponse(HttpServletRequest request, HttpServletResponse response, HttpStatus status) throws IOException {
        Gson gson = new Gson();
        Exception exception = (Exception) request.getAttribute("exception");
        ErrorResponse errorResponse;

        if (exception instanceof ExpiredJwtException) {
            errorResponse = ErrorResponse.of(ExceptionCode.INVALID_ACCESS_TOKEN);
        } else errorResponse = ErrorResponse.of(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }
}
