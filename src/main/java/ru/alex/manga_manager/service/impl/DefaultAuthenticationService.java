package ru.alex.manga_manager.service.impl;

import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import ru.alex.manga_manager.model.data.user.Role;
import ru.alex.manga_manager.model.data.Token;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.LoginUserDto;
import ru.alex.manga_manager.model.dto.user.RegistrationUserDto;
import ru.alex.manga_manager.model.response.Tokens;
import ru.alex.manga_manager.security.jwt.factory.AccessFactory;
import ru.alex.manga_manager.security.jwt.factory.DefaultAccessFactory;
import ru.alex.manga_manager.security.jwt.factory.DefaultRefreshFactory;
import ru.alex.manga_manager.security.jwt.factory.RefreshFactory;
import ru.alex.manga_manager.security.jwt.serializer.AccessTokenJwsStringSerializer;
import ru.alex.manga_manager.security.jwt.serializer.DefaultAccessTokenJwsStringSerializer;
import ru.alex.manga_manager.security.jwt.serializer.DefaultRefreshTokenJweStringSerializer;
import ru.alex.manga_manager.security.jwt.serializer.RefreshTokenJweStringSerializer;
import ru.alex.manga_manager.service.AuthenticationService;
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
public class DefaultAuthenticationService implements AuthenticationService {

    @Qualifier("defaultUserService")
    private final UserService userService;

    private final UserValidator userValidator;

    private final AccessFactory accessFactory = new DefaultAccessFactory();

    private final RefreshFactory refreshFactory = new DefaultRefreshFactory();

    private final AccessTokenJwsStringSerializer accessTokenJwsStringSerializer;

    private final RefreshTokenJweStringSerializer refreshTokenJweStringSerializer;

    @Qualifier("defaultAuthenticationProvider")
    private final AuthenticationProvider authenticationProvider;

    public DefaultAuthenticationService(UserService userService,
                                        UserValidator userValidator,
                                        @Value("${jwt.secret.access}") String access,
                                        @Value("${jwt.secret.refresh}") String refresh,
                                        AuthenticationProvider authenticationProvider) throws ParseException, KeyLengthException {
        this.userService = userService;
        this.userValidator = userValidator;
        this.accessTokenJwsStringSerializer = new DefaultAccessTokenJwsStringSerializer(
                new MACSigner(OctetSequenceKey.parse(access))
        );
        this.refreshTokenJweStringSerializer = new DefaultRefreshTokenJweStringSerializer(
                new DirectEncrypter(OctetSequenceKey.parse(refresh))
        );

        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public Tokens registration(RegistrationUserDto userDto, BindingResult bindingResult) {

        List<Validator> validators = List.of(this.userValidator, new MailValidator(), new PasswordValidator());

        for (var i : validators) {
            i.validate(userDto, bindingResult);
        }
        if (bindingResult.hasErrors()) {
            throw new RegistrationException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        User user = this.userService.save(userDto);

        Authentication authentication =
                new PreAuthenticatedAuthenticationToken(user.getId(), user.getPassword(), user.getRoles().stream()
                        .map((Role role) -> new SimpleGrantedAuthority(role.getRole())).toList());


        Token refresh = refreshFactory.apply(authentication);
        Token access = accessFactory.apply(refresh);

        return new Tokens(this.accessTokenJwsStringSerializer.apply(access),
                access.expireAt().toString(),
                this.refreshTokenJweStringSerializer.apply(refresh),
                refresh.expireAt().toString());
    }

    @Override
    @Transactional(readOnly = true)
    public Tokens login(LoginUserDto userDto, BindingResult bindingResult) {
        User user = userService.findByEmail(userDto.getEmail());

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(user.getEmail(),
                        user.getPassword(),
                        user.getRoles().stream().map(r ->
                                new SimpleGrantedAuthority(r.getRole())).toList());

        authenticationProvider.authenticate(authentication);

        Token refresh = refreshFactory.apply(new PreAuthenticatedAuthenticationToken(user.getId(),
                user.getPassword(),
                user.getRoles().stream().map(r ->
                        new SimpleGrantedAuthority(r.getRole())).toList()));

        Token access = accessFactory.apply(refresh);

        return new Tokens(this.accessTokenJwsStringSerializer.apply(access),
                access.expireAt().toString(),
                this.refreshTokenJweStringSerializer.apply(refresh),
                refresh.expireAt().toString());
    }


}
