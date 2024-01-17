package ru.alex.manga_manager.util.exception.handler;

import ru.alex.manga_manager.model.response.ErrorResponse;
import java.time.ZonedDateTime;

public class UserNotFoundExceptionHandler implements ExceptionHandlerStrategy {

    @Override
    public ErrorResponse handleException(RuntimeException e) {
        return ErrorResponse.builder()
                .code("404")
                .error(e.getClass().getName())
                .message(e.getLocalizedMessage())
                .timestamp(ZonedDateTime.now()).build();
    }
}
