package ru.alex.manga_manager.util.exception.handler;


import ru.alex.manga_manager.model.response.ErrorResponse;

public interface ExceptionHandlerStrategy {

    ErrorResponse handleException(RuntimeException e);
}
