package ru.alex.manga_manager.web.controller_advice;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        this.handler.put(RoleNotFoundException.class, new RoleNotFoundExceptionHandler());
        this.handler.put(UserNotFoundException.class, new UserNotFoundExceptionHandler());
        this.handler.put(ResourceNotFoundException.class, new ResourceNotFoundExceptionHandler());
        this.handler.put(MangaNotFoundException.class, new MangaNotFoundExceptionHandler());
        this.handler.put(CommentAddException.class, new CommentAddExceptionHandler());
    }

    @ExceptionHandler({RoleNotFoundException.class,
            UserNotFoundException.class,
            ResourceNotFoundException.class,
            MangaNotFoundException.class,
            CommentAddException.class,
    })
    public ResponseEntity<ErrorResponse> exHandler(RuntimeException e) {
        ExceptionHandlerStrategy exceptionHandlerStrategy = handler.get(e.getClass());
        ErrorResponse errorResponse = exceptionHandlerStrategy.handleException(e);
        return ResponseEntity.status(Integer.parseInt(errorResponse.code)).body(errorResponse);
    }
}
