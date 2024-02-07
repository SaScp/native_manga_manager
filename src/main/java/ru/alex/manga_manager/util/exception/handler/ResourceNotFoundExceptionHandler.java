package ru.alex.manga_manager.util.exception.handler;

import ru.alex.manga_manager.model.response.ErrorResponse;
import java.time.ZonedDateTime;

public class ResourceNotFoundExceptionHandler implements ExceptionHandlerStrategy {

    @Override
    public ErrorResponse handleException(RuntimeException e) {
        return ErrorResponse.builder()
                .code("404")
                .message(e.getLocalizedMessage())
                .timestamp(ZonedDateTime.now()).build();
    }
}
