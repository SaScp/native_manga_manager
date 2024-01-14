package ru.alex.manga_manager.security.jwt.factory;


import ru.alex.manga_manager.model.data.Token;

import java.util.function.Function;

public interface AccessFactory extends Function<Token, Token> {
}
