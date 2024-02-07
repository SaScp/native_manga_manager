package ru.alex.manga_manager.security.jwt.factory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import ru.alex.manga_manager.model.data.Token;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.UUID;

public class DefaultRefreshFactory implements RefreshFactory {

    private final Duration tokenTtl = Duration.ofDays(1);

    @Override
    public Token apply(Authentication authentication) {
        LinkedList<String> authorities = new LinkedList<>();
        authorities.add("JWT_REFRESH");
        authorities.add("JWT_LOGOUT");
        authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(auth -> "GRAND_" + auth)
                .forEach(authorities::add);

        var now = Instant.now();
        return new Token(UUID.randomUUID().toString(), authentication.getName(), authorities, now, now.plus(tokenTtl));
    }
}
