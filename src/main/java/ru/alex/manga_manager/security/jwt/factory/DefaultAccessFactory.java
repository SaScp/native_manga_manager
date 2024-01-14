package ru.alex.manga_manager.security.jwt.factory;

import lombok.Setter;
import ru.alex.manga_manager.model.data.Token;

import java.time.Duration;
import java.time.Instant;

@Setter
public class DefaultAccessFactory implements AccessFactory {

    private Duration duration = Duration.ofMinutes(5);
    @Override
    public Token apply(Token token) {
        var now = Instant.now();
        return new Token(token.id(), token.subject(), token.authorities().stream()
                .filter(auth -> auth.startsWith("GRAND_"))
                .map(auth -> auth.replace("GRAND_", "")).toList(),now, now.plus(duration));
    }

}
