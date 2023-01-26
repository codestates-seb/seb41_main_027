package main027.server.global.auth.handler;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import main027.server.global.advice.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MemberAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        log.error("# Authentication failed: {}", exception.getMessage());

        sendErrorResponse(response, exception);
    }

    private void sendErrorResponse(HttpServletResponse response, AuthenticationException exception) throws IOException {
        Gson gson = new Gson();
        ErrorResponse errorResponse;

        if (exception.getClass().equals(BadCredentialsException.class)) {
            String message = "password do not match";
            errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, message);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else if (exception.getClass().equals(InternalAuthenticationServiceException.class)){
            errorResponse = ErrorResponse.of(HttpStatus.NOT_FOUND, exception.getMessage());
            response.setStatus(HttpStatus.NOT_FOUND.value());
        } else {
            errorResponse = ErrorResponse.of(HttpStatus.UNAUTHORIZED);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }


        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }
}
