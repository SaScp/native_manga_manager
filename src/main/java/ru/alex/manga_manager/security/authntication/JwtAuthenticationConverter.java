package ru.alex.manga_manager.security.authntication;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import ru.alex.manga_manager.model.data.Token;
import ru.alex.manga_manager.security.jwt.deserializer.AccessTokenJwsStringDeserializer;
import ru.alex.manga_manager.security.jwt.deserializer.RefreshTokenJwsStringDeserializer;


public class JwtAuthenticationConverter implements AuthenticationConverter {

    private final RefreshTokenJwsStringDeserializer refreshTokenJwsStringDeserializer;

    private final AccessTokenJwsStringDeserializer accessTokenJwsStringDeserializer;

    public JwtAuthenticationConverter(RefreshTokenJwsStringDeserializer refreshTokenJwsStringDeserializer,
                                      AccessTokenJwsStringDeserializer accessTokenJwsStringDeserializer) {
        this.refreshTokenJwsStringDeserializer = refreshTokenJwsStringDeserializer;
        this.accessTokenJwsStringDeserializer = accessTokenJwsStringDeserializer;
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            Token accessToken = this.accessTokenJwsStringDeserializer.apply(token);
            if (accessToken != null) {
                return new PreAuthenticatedAuthenticationToken(accessToken, token);
            }
            Token refreshToken = this.refreshTokenJwsStringDeserializer.apply(token);
            if (refreshToken != null) {
                return new PreAuthenticatedAuthenticationToken(refreshToken, token);
            }
        }

        return null;
    }
}
