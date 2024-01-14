package ru.alex.manga_manager.model.response;

public record Tokens(String accessToken, String accessTokenExp, String refreshToken, String refreshTokenExp) {
}
