package ru.alex.manga_manager.web.controller_advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.alex.manga_manager.model.response.ErrorResponse;
import ru.alex.manga_manager.util.exception.*;
import ru.alex.manga_manager.util.exception.handler.*;


import java.util.HashMap;

@RestControllerAdvice
public class ControllerAdvice {

    private final HashMap<Class<? extends RuntimeException>, ExceptionHandlerStrategy> handler;

    public ControllerAdvice() {
        this.handler = new HashMap<>();
        this.handler.put(RegistrationException.class, new RegistrationExceptionHandler());
        this.handler.put(RoleNotFoundException.class, new RoleNotFoundExceptionHandler());
        this.handler.put(UserNotFoundException.class, new UserNotFoundExceptionHandler());
        this.handler.put(ResourceNotFoundException.class, new ResourceNotFoundExceptionHandler());
        this.handler.put(ForbiddenException.class, new ForbiddenExceptionHandler());
        this.handler.put(MangaNotFoundException.class, new MangaNotFoundExceptionHandler());
    }

    @ExceptionHandler({RegistrationException.class,
            RoleNotFoundException.class,
            UserNotFoundException.class,
            ResourceNotFoundException.class,
            ForbiddenException.class,
            MangaNotFoundException.class
    })
    public ErrorResponse exHandler(RuntimeException e) {
        ExceptionHandlerStrategy exceptionHandlerStrategy = handler.get(e.getClass());
        return exceptionHandlerStrategy.handleException(e);
    }
}
