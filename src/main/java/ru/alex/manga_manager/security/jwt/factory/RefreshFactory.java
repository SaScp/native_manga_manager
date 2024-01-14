package ru.alex.manga_manager.security.jwt.factory;

import org.springframework.security.core.Authentication;
import ru.alex.manga_manager.model.data.Token;

import java.util.function.Function;

public interface RefreshFactory extends Function<Authentication, Token> {
}
