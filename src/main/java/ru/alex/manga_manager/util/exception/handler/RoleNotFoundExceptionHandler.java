package ru.alex.manga_manager.util.exception.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import ru.alex.manga_manager.model.response.ErrorResponse;
import ru.alex.manga_manager.util.exception.RoleNotFoundException;

import java.time.ZonedDateTime;

@Component
public class RoleNotFoundExceptionHandler implements ExceptionHandlerStrategy {

    @Override
    public ErrorResponse handleException(RuntimeException e, WebRequest webRequest) {
        return ErrorResponse.builder()
                .code("404")
                .path(webRequest.getDescription(false))
                .message(e.getLocalizedMessage())
                .timestamp(ZonedDateTime.now()).build();
    }

    @Override
    public Class<? extends RuntimeException> getExceptionClass() {
        return RoleNotFoundException.class;
    }
}