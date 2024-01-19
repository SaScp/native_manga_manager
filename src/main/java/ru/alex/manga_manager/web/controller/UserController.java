package ru.alex.manga_manager.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.alex.manga_manager.model.data.User;
import ru.alex.manga_manager.model.dto.UserDto;
import ru.alex.manga_manager.service.UserService;
import ru.alex.manga_manager.util.converter.UserConverter;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    @Qualifier("defaultUserService")
    private final UserService userService;

    private final UserConverter<UserDto, User> userDtoConverter;

    @GetMapping("/")
    public UserDto findUserByAuthentication(Authentication authentication) {
        return userDtoConverter.convert(userService.findUserByAuthentication(authentication));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/updatePassword")
    public HttpStatus update(@RequestBody Optional<String> password,
                             Authentication authentication) {
        return null;
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add/{id}")
    public HttpStatus add(@PathVariable String id, Authentication authentication) {
        return userService.add(id, authentication) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }
}
