package ru.alex.manga_manager.util.exception.handler;

import ru.alex.manga_manager.model.response.ErrorResponse;

import java.time.ZonedDateTime;

public class MangaNotFoundExceptionHandler implements ExceptionHandlerStrategy{
    @Override
    public ErrorResponse handleException(RuntimeException e) {
        return ErrorResponse.builder()
                .code("404")
                .error(e.getClass().toString())
                .timestamp(ZonedDateTime.now())
                .message(e.getMessage()).build();
    }
}