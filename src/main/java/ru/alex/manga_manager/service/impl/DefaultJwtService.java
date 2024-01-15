package ru.alex.manga_manager.service.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alex.manga_manager.security.jwt.deserializer.AccessTokenJwsStringDeserializer;
import ru.alex.manga_manager.security.jwt.deserializer.DefaultAccessTokenJwsStringDeserializer;
import ru.alex.manga_manager.security.jwt.deserializer.DefaultRefreshTokenJwsStringDeserializer;
import ru.alex.manga_manager.security.jwt.deserializer.RefreshTokenJwsStringDeserializer;
import ru.alex.manga_manager.service.JwtService;

import java.text.ParseException;

@Service
public class DefaultJwtService implements JwtService {
    private final AccessTokenJwsStringDeserializer accessTokenJwsStringDeserializer;

    private final RefreshTokenJwsStringDeserializer refreshTokenJwsStringDeserializer;

    public DefaultJwtService(@Value("${jwt.secret.access}") String accessToken,
                             @Value("${jwt.secret.refresh}") String refreshToken) throws ParseException, JOSEException {
        this.accessTokenJwsStringDeserializer = new DefaultAccessTokenJwsStringDeserializer(new MACVerifier(OctetSequenceKey.parse(accessToken)));
        this.refreshTokenJwsStringDeserializer = new DefaultRefreshTokenJwsStringDeserializer(new DirectDecrypter(OctetSequenceKey.parse(refreshToken)));
    }
}
