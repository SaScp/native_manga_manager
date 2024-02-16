package ru.alex.manga_manager.util.exception.handler;


import org.springframework.web.context.request.WebRequest;
import ru.alex.manga_manager.model.response.ErrorResponse;

public interface ExceptionHandlerStrategy {

    ErrorResponse handleException(RuntimeException e, WebRequest webRequest);

    Class<? extends RuntimeException> getExceptionClass();
}
