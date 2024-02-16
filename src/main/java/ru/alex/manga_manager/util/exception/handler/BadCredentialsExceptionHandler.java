package ru.alex.manga_manager.util.exception.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.context.request.WebRequest;
import ru.alex.manga_manager.model.response.ErrorResponse;

import java.time.ZonedDateTime;

public class BadCredentialsExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ErrorResponse handleException(RuntimeException e, WebRequest webRequest) {
        return ErrorResponse.builder()
                .code("401")
                .path(webRequest.getDescription(false))
                .timestamp(ZonedDateTime.now())
                .message(e.getMessage()).build();
    }

    @Override
    public Class<? extends RuntimeException> getExceptionClass() {
        return BadCredentialsException.class;
    }
}
