package ru.alex.manga_manager.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alex.manga_manager.model.dto.user.UserDto;
import ru.alex.manga_manager.model.response.Tokens;
import ru.alex.manga_manager.service.LoginService;
import ru.alex.manga_manager.service.RegistrationService;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Qualifier("defaultRegistrationService")
    private final RegistrationService registrationService;

    private final LoginService loginService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public Tokens registration(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) throws URISyntaxException {
        return registrationService.registration(userDto, bindingResult);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Tokens login(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        return loginService.login(userDto, bindingResult);
    }
}
