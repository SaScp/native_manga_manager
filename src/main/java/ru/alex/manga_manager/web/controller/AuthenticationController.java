package ru.alex.manga_manager.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alex.manga_manager.model.dto.user.LoginUserDto;
import ru.alex.manga_manager.model.dto.user.RegistrationUserDto;
import ru.alex.manga_manager.model.response.Tokens;
import ru.alex.manga_manager.service.AuthenticationService;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Qualifier("defaultAuthenticationService")
    private final AuthenticationService authenticationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public Tokens registration(@Valid @RequestBody RegistrationUserDto userDto, BindingResult bindingResult) throws URISyntaxException {
        return authenticationService.registration(userDto, bindingResult);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tokens> login(@Valid @RequestBody LoginUserDto userDto, BindingResult bindingResult) {
        return ResponseEntity
                .accepted()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticationService.login(userDto, bindingResult));
    }
}
