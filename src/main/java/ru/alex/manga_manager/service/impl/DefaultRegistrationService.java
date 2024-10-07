package ru.alex.manga_manager.service.impl;

import com.nimbusds.jose.KeyLengthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import ru.alex.manga_manager.model.data.user.Role;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UserDto;
import ru.alex.manga_manager.model.response.Tokens;
import ru.alex.manga_manager.service.JwtService;
import ru.alex.manga_manager.service.RegistrationService;
import ru.alex.manga_manager.service.UserService;
import ru.alex.manga_manager.util.exception.RegistrationException;
import ru.alex.manga_manager.util.validator.MailValidator;
import ru.alex.manga_manager.util.validator.PasswordValidator;
import ru.alex.manga_manager.util.validator.UserValidator;


import java.text.ParseException;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
public class DefaultRegistrationService implements RegistrationService {

    @Qualifier("defaultUserService")
    private final UserService userService;

    private final UserValidator userValidator;

    private final JwtService jwtService;


    public DefaultRegistrationService(UserService userService,
                                      UserValidator userValidator,
                                      JwtService jwtService) throws ParseException, KeyLengthException {
        this.userService = userService;
        this.userValidator = userValidator;
        this.jwtService = jwtService;
    }

    @Override
    public Tokens registration(UserDto userDto, BindingResult bindingResult) {

        List<Validator> validators = List.of(this.userValidator, new MailValidator(), new PasswordValidator());

        for (var i : validators) {
            i.validate(userDto, bindingResult);
        }
        if (bindingResult.hasErrors()) {
            throw new RegistrationException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        User user = this.userService.save(userDto);

        Authentication authentication =
                new PreAuthenticatedAuthenticationToken(user.getEmail(), user.getPassword(), user.getRoles().stream()
                        .map((Role role) -> new SimpleGrantedAuthority(role.getRole())).toList());


        return jwtService.createTokens(authentication);
    }


}
