package ru.alex.manga_manager.util.exception.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import ru.alex.manga_manager.model.response.ErrorResponse;
import ru.alex.manga_manager.util.exception.ForbiddenException;

import java.time.ZonedDateTime;

@Component
public class ForbiddenExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ErrorResponse handleException(RuntimeException e, WebRequest webRequest) {
        return ErrorResponse.builder()
                .code("403")
                .path(webRequest.getDescription(false))
                .message("Forbidden")
                .timestamp(ZonedDateTime.now())
                .build();
    }

    @Override
    public Class<? extends RuntimeException> getExceptionClass() {
        return ForbiddenException.class;
    }
}
