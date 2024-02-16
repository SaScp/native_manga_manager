package ru.alex.manga_manager.util.exception.handler;

import org.springframework.web.context.request.WebRequest;
import ru.alex.manga_manager.model.response.ErrorResponse;
import ru.alex.manga_manager.util.exception.ResourceNotFoundException;

import java.time.ZonedDateTime;

public class ResourceNotFoundExceptionHandler implements ExceptionHandlerStrategy {

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
        return ResourceNotFoundException.class;
    }
}
