package ru.alex.manga_manager.security.jwt.serializer;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.extern.slf4j.Slf4j;
import ru.alex.manga_manager.model.data.Token;


import java.util.Date;

@Slf4j
public class DefaultRefreshTokenJweStringSerializer implements RefreshTokenJweStringSerializer {

    private final JWEEncrypter jweEncrypter;

    private JWEAlgorithm jweAlgorithm = JWEAlgorithm.DIR;

    private EncryptionMethod encryptionMethod = EncryptionMethod.A128GCM;

    public DefaultRefreshTokenJweStringSerializer(JWEEncrypter jweEncrypter) {
        this.jweEncrypter = jweEncrypter;
    }

    public DefaultRefreshTokenJweStringSerializer(JWEEncrypter jweEncrypter, EncryptionMethod encryptionMethod) {
        this.jweEncrypter = jweEncrypter;
        this.encryptionMethod = encryptionMethod;
    }

    public DefaultRefreshTokenJweStringSerializer(JWEEncrypter jweEncrypter, JWEAlgorithm jwsAlgorithm) {
        this.jweEncrypter = jweEncrypter;
        this.jweAlgorithm = jwsAlgorithm;
    }

    public DefaultRefreshTokenJweStringSerializer(JWEEncrypter jweEncrypter, JWEAlgorithm jwsAlgorithm, EncryptionMethod encryptionMethod) {
        this.jweEncrypter = jweEncrypter;
        this.jweAlgorithm = jwsAlgorithm;
        this.encryptionMethod = encryptionMethod;
    }

    @Override
    public String apply(Token token) {
        JWEHeader jwsHeader = new JWEHeader.Builder(this.jweAlgorithm, this.encryptionMethod)
                .keyID(token.id()).build();
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(token.id())
                .subject(token.subject())
                .issueTime(Date.from(token.createAt()))
                .expirationTime(Date.from(token.expireAt()))
                .claim("authorities", token.authorities())
                .build();
        EncryptedJWT encryptedJWT = new EncryptedJWT(jwsHeader, jwtClaimsSet);
        try {
            encryptedJWT.encrypt(this.jweEncrypter);
            String a = encryptedJWT.serialize();
            return a;
        } catch (JOSEException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
