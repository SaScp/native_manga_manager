package ru.alex.manga_manager.security.jwt.serializer;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import ru.alex.manga_manager.model.data.Token;

import java.util.Date;

@Slf4j
public class DefaultAccessTokenJwsStringSerializer implements AccessTokenJwsStringSerializer {

    private final JWSSigner jwsSigner;

    private JWSAlgorithm jwsAlgorithm = JWSAlgorithm.HS256;

    public DefaultAccessTokenJwsStringSerializer(JWSSigner jwsSigner) {
        this.jwsSigner = jwsSigner;
    }

    public DefaultAccessTokenJwsStringSerializer(JWSSigner jwsSigner, JWSAlgorithm jwsAlgorithm) {
        this.jwsSigner = jwsSigner;
        this.jwsAlgorithm = jwsAlgorithm;
    }
    @Override
    public String apply(Token token) {
        JWSHeader jwsHeader = new JWSHeader.Builder(this.jwsAlgorithm)
                .keyID(token.id()).build();
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(token.id())
                .subject(token.subject())
                .issueTime(Date.from(token.createAt()))
                .expirationTime(Date.from(token.expireAt()))
                .claim("authorities", token.authorities()).build();
        SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);
        try {
            signedJWT.sign(this.jwsSigner);
            return signedJWT.serialize();

        } catch (JOSEException e) {
           log.error(e.getMessage(), e);
        }

        return null;
    }
}
