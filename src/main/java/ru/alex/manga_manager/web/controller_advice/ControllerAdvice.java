package ru.alex.manga_manager.web.controller_advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.alex.manga_manager.model.response.ErrorResponse;
import ru.alex.manga_manager.util.exception.RegistrationException;
import ru.alex.manga_manager.util.exception.ResourceNotFoundException;
import ru.alex.manga_manager.util.exception.RoleNotFoundException;
import ru.alex.manga_manager.util.exception.UserNotFoundException;
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
    }

    @ExceptionHandler({RegistrationException.class,
            RoleNotFoundException.class,
            UserNotFoundException.class,
            ResourceNotFoundException.class
    })
    public ErrorResponse exHandler(RuntimeException e) {
        ExceptionHandlerStrategy exceptionHandlerStrategy = handler.get(e.getClass());
        return exceptionHandlerStrategy.handleException(e);
    }
}
