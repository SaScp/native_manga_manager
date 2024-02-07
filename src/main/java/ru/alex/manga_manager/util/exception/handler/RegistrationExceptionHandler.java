package ru.alex.manga_manager.util.exception.handler;



import ru.alex.manga_manager.model.response.ErrorResponse;

import java.time.ZonedDateTime;

public class RegistrationExceptionHandler implements ExceptionHandlerStrategy {

    @Override
    public ErrorResponse handleException(RuntimeException e) {
        return ErrorResponse.builder()
                .code("401")
                .message(e.getLocalizedMessage())
                .timestamp(ZonedDateTime.now()).build();
    }
}
