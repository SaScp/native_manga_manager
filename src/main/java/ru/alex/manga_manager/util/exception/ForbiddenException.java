package ru.alex.manga_manager.util.exception;

import org.springframework.security.core.AuthenticationException;

public class ForbiddenException extends AuthenticationException {
    public ForbiddenException(String message) {
        super(message);
    }
}
