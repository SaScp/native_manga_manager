package ru.alex.manga_manager.security.authntication;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.Token;

import java.time.Instant;

@Component
public class TokenAuthUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final JdbcTemplate jdbcTemplate;

    public TokenAuthUserDetailsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken authenticationToken) throws UsernameNotFoundException {
        if (authenticationToken.getPrincipal() instanceof Token token ) {

            TokenUser tokenUser = new TokenUser(token.subject(), "nopassword", true, true,
                    !this.jdbcTemplate.queryForObject("""
                            select exists(select id from t_logout where id = ?)
                            """, Boolean.class, token.id()) &&
                            token.expireAt().isAfter(Instant.now()), true,
                    token.authorities().stream().map(SimpleGrantedAuthority::new)
                            .toList(), token);
            return tokenUser;
        }
        throw new UsernameNotFoundException("Principal must me of type Token");
    }
}
