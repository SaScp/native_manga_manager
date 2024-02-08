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
public class AuthenticationControllerAdvice {

    private final HashMap<Class<? extends RuntimeException>, ExceptionHandlerStrategy> handler;

    public AuthenticationControllerAdvice() {
        this.handler = new HashMap<>();

        this.handler.put(ForbiddenException.class, new ForbiddenExceptionHandler());
        this.handler.put(BadCredentialsException.class, new BadCredentialsExceptionHandler());
        this.handler.put(UsernameNotFoundException.class, new UsernameNotFoundExceptionHandler());
        this.handler.put(LoginException.class, new LoginExceptionHandler());
        this.handler.put(RegistrationException.class, new RegistrationExceptionHandler());
    }

    @ExceptionHandler({RegistrationException.class,
            ForbiddenException.class,
            BadCredentialsException.class,
            UsernameNotFoundException.class,
            LoginException.class
    })
    public ResponseEntity<ErrorResponse> exHandler(RuntimeException e) {
        ExceptionHandlerStrategy exceptionHandlerStrategy = handler.get(e.getClass());
        ErrorResponse errorResponse = exceptionHandlerStrategy.handleException(e);
        return ResponseEntity.status(Integer.parseInt(errorResponse.code)).body(errorResponse);
    }
}
