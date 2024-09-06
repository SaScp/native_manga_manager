package ru.alex.manga_manager.web.controller_advice;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.alex.manga_manager.model.response.ErrorResponse;
import ru.alex.manga_manager.util.exception.*;
import ru.alex.manga_manager.util.exception.handler.*;


import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    private final HashMap<Class<? extends RuntimeException>, ExceptionHandlerStrategy> handler;

    public ControllerAdvice(List<ExceptionHandlerStrategy> handlerStrategyList) {
        this.handler = new HashMap<>();

        for (var i : handlerStrategyList) {
            this.handler.put(i.getExceptionClass(), i);
        }

    }
    @ExceptionHandler({RoleNotFoundException.class,
            UserNotFoundException.class,
            ResourceNotFoundException.class,
            MangaNotFoundException.class,
            CommentAddException.class
    })
    public ResponseEntity<ErrorResponse> otherExceptionHandler(RuntimeException e, WebRequest webRequest) {
        ExceptionHandlerStrategy exceptionHandlerStrategy = handler.get(e.getClass());
        ErrorResponse errorResponse = exceptionHandlerStrategy.handleException(e, webRequest);
        return ResponseEntity.status(Integer.parseInt(errorResponse.getCode())).body(errorResponse);
    }

    @ExceptionHandler({RegistrationException.class,
            ForbiddenException.class,
            BadCredentialsException.class,
            UsernameNotFoundException.class,
            LoginException.class
    })
    public ResponseEntity<ErrorResponse> AuthenticationExceptionHandler(RuntimeException e, WebRequest webRequest) {
        ExceptionHandlerStrategy exceptionHandlerStrategy = handler.get(e.getClass());
        ErrorResponse errorResponse = exceptionHandlerStrategy.handleException(e, webRequest);
        return ResponseEntity.status(Integer.parseInt(errorResponse.getCode())).body(errorResponse);
    }

    /*@ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception e, WebRequest webRequest) {
        return ResponseEntity.internalServerError().body(ErrorResponse.builder()
                .path(webRequest.getDescription(false))
                .code("500")
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now()).build());
    }*/
}
