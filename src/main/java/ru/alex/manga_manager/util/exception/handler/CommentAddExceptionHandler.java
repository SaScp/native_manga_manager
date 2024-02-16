package ru.alex.manga_manager.util.exception.handler;

import org.springframework.web.context.request.WebRequest;
import ru.alex.manga_manager.model.response.ErrorResponse;
import ru.alex.manga_manager.util.exception.CommentAddException;

import java.time.ZonedDateTime;

public class CommentAddExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ErrorResponse handleException(RuntimeException e, WebRequest webRequest) {
        return ErrorResponse.builder()
                .code("500")
                .path(webRequest.getDescription(false))
                .timestamp(ZonedDateTime.now())
                .message(e.getMessage()).build();
    }

    @Override
    public Class<? extends RuntimeException> getExceptionClass() {
        return CommentAddException.class;
    }
}
