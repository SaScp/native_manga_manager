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

    private HashMap<Class<? extends RuntimeException>, ExceptionHandlerStrategy> runtimeExceptionHashMap;

    public ControllerAdvice() {
        this.runtimeExceptionHashMap = new HashMap<>();
        this.runtimeExceptionHashMap.put(RegistrationException.class, new RegistrationExceptionHandler());
        this.runtimeExceptionHashMap.put(RoleNotFoundException.class, new RoleNotFoundExceptionHandler());
        this.runtimeExceptionHashMap.put(UserNotFoundException.class, new UserNotFoundExceptionHandler());
        this.runtimeExceptionHashMap.put(ResourceNotFoundException.class, new ResourceNotFoundExceptionHandler());
    }

    @ExceptionHandler({RegistrationException.class,
            RoleNotFoundException.class,
            UserNotFoundException.class,
            ResourceNotFoundException.class
    })
    public ErrorResponse exHandler(RuntimeException e) {
        ExceptionHandlerStrategy exceptionHandlerStrategy = runtimeExceptionHashMap.get(e.getClass());
        return exceptionHandlerStrategy.handleException(e);
    }
}
