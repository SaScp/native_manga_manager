package ru.alex.manga_manager.util.exception;

import org.springframework.security.core.AuthenticationException;

public class RegistrationException extends AuthenticationException {
    public RegistrationException(String msg) {
        super(msg);
    }
}
