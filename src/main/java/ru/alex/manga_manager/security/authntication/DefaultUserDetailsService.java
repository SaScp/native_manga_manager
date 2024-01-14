package ru.alex.manga_manager.security.authntication;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.service.UserService;


@Component
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {
    @Qualifier("defaultUserService")
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final var user = userService.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return DefaultUserDetails
                .builder()
                .user(user)
                .authorities(user.getRoles().stream().map(r-> new SimpleGrantedAuthority(r.getRole())).toList())
                .build();
    }
}
