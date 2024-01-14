package ru.alex.manga_manager.security.jwt.serializer;



import ru.alex.manga_manager.model.data.Token;

import java.util.function.Function;

public interface RefreshTokenJweStringSerializer extends Function<Token, String> {
}
