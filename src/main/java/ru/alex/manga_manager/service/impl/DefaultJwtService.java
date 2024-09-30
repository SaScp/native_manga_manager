package ru.alex.manga_manager.service.impl;

import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.alex.manga_manager.model.data.Token;
import ru.alex.manga_manager.model.response.Tokens;
import ru.alex.manga_manager.security.jwt.factory.AccessFactory;
import ru.alex.manga_manager.security.jwt.factory.DefaultAccessFactory;
import ru.alex.manga_manager.security.jwt.factory.DefaultRefreshFactory;
import ru.alex.manga_manager.security.jwt.factory.RefreshFactory;
import ru.alex.manga_manager.security.jwt.serializer.AccessTokenJwsStringSerializer;
import ru.alex.manga_manager.security.jwt.serializer.DefaultAccessTokenJwsStringSerializer;
import ru.alex.manga_manager.security.jwt.serializer.DefaultRefreshTokenJweStringSerializer;
import ru.alex.manga_manager.security.jwt.serializer.RefreshTokenJweStringSerializer;
import ru.alex.manga_manager.service.JwtService;

import java.text.ParseException;

@Service
public class DefaultJwtService implements JwtService {

    private final AccessTokenJwsStringSerializer accessTokenJwsStringSerializer;

    private final RefreshTokenJweStringSerializer refreshTokenJweStringSerializer;

    private final AccessFactory accessFactory;

    private final RefreshFactory refreshFactory;

    public DefaultJwtService(@Qualifier("defaultAccessFactory") AccessFactory accessFactory,
                          @Qualifier("defaultRefreshFactory") RefreshFactory refreshFactory,
                          AccessTokenJwsStringSerializer accessTokenJwsStringSerializer,
                          RefreshTokenJweStringSerializer refreshTokenJweStringSerializer
    ) {

        this.accessTokenJwsStringSerializer = accessTokenJwsStringSerializer;
        this.refreshTokenJweStringSerializer = refreshTokenJweStringSerializer;
        this.accessFactory = accessFactory;
        this.refreshFactory = refreshFactory;
    }


    public Tokens createTokens(final Authentication authentication) {
        Token refresh = refreshFactory.apply(authentication);
        Token access = accessFactory.apply(refresh);
        return new Tokens(this.accessTokenJwsStringSerializer.apply(access),
                access.expireAt().toString(),
                this.refreshTokenJweStringSerializer.apply(refresh),
                refresh.expireAt().toString());
    }

}
