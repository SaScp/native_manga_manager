package ru.alex.manga_manager.service.impl;

import com.nimbusds.jose.KeyLengthException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UserDto;
import ru.alex.manga_manager.model.response.Tokens;
import ru.alex.manga_manager.service.JwtService;
import ru.alex.manga_manager.service.LoginService;
import ru.alex.manga_manager.service.UserService;

import java.text.ParseException;

@Service
public class DefaultLoginService implements LoginService {

    @Qualifier("defaultUserService")
    private final UserService userService;

    private final JwtService jwtService;

    @Qualifier("defaultAuthenticationProvider")
    private final AuthenticationProvider authenticationProvider;

    public DefaultLoginService(UserService userService,
                               @Value("${jwt.secret.access}") String access,
                               @Value("${jwt.secret.refresh}") String refresh,@Qualifier("defaultJwtService") JwtService jwtService,
                               AuthenticationProvider authenticationProvider) throws ParseException, KeyLengthException {
        this.userService = userService;
        this.jwtService = jwtService;

        this.authenticationProvider = authenticationProvider;
    }


    @Override
    @Transactional(readOnly = true)
    public Tokens login(UserDto userDto, BindingResult bindingResult) {
        User user = userService.findByEmail(userDto.getEmail());

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(user.getEmail(),
                        user.getPassword(),
                        user.getRoles().stream().map(r ->
                                new SimpleGrantedAuthority(r.getRole())).toList());

        authenticationProvider.authenticate(authentication);


        return jwtService.createTokens(authentication);
    }
}
