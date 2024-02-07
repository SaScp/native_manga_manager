package ru.alex.manga_manager.util.exception.handler;

import ru.alex.manga_manager.model.response.ErrorResponse;

import java.time.ZonedDateTime;

public class CommentNotFoundExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ErrorResponse handleException(RuntimeException e) {
        return ErrorResponse.builder()
                .error(e.getClass().getName())
                .code("500")
                .timestamp(ZonedDateTime.now())
                .message(e.getMessage()).build();
    }
}
