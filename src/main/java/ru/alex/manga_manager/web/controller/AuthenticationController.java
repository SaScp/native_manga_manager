package ru.alex.manga_manager.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.manga_manager.model.dto.LoginUserDto;
import ru.alex.manga_manager.model.dto.UserDto;
import ru.alex.manga_manager.model.response.Tokens;
import ru.alex.manga_manager.service.AuthenticationService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Qualifier("defaultAuthenticationService")
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<Tokens> registration(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) throws URISyntaxException {
        return ResponseEntity
                .created(new URI("v1/auth/registration"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticationService.registration(userDto, bindingResult));
    }

    @PostMapping("/login")
    public ResponseEntity<Tokens> login(@Valid @RequestBody LoginUserDto userDto, BindingResult bindingResult) {
        return ResponseEntity
                .accepted()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticationService.login(userDto, bindingResult));
    }
}
