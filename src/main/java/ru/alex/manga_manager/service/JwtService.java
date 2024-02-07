package ru.alex.manga_manager.service;

import org.springframework.security.core.Authentication;
import ru.alex.manga_manager.model.response.Tokens;

public interface JwtService {

    Tokens createTokens(final Authentication authentication);
}
