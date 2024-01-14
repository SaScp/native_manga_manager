package ru.alex.manga_manager.security.jwt.deserializer;


import ru.alex.manga_manager.model.data.Token;

import java.util.function.Function;

public interface RefreshTokenJwsStringDeserializer extends Function<String, Token> {
}
