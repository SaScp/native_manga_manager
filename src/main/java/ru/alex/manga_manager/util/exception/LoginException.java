package ru.alex.manga_manager.util.exception;

import org.springframework.security.core.AuthenticationException;

public class LoginException extends AuthenticationException {
    public LoginException(String defaultMessage) {
        super(defaultMessage);
    }
}
