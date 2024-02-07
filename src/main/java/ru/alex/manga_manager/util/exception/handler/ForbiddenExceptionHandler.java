package ru.alex.manga_manager.util.exception.handler;

import ru.alex.manga_manager.model.response.ErrorResponse;

import java.time.ZonedDateTime;

public class ForbiddenExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ErrorResponse handleException(RuntimeException e) {
        return ErrorResponse.builder()
                .code("403")
                .error(e.getClass().getName())
                .message("Forbidden")
                .timestamp(ZonedDateTime.now())
                .build();
    }
}
