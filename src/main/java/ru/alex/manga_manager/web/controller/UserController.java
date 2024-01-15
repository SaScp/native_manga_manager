package ru.alex.manga_manager.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.manga_manager.model.data.User;
import ru.alex.manga_manager.model.dto.UserDto;
import ru.alex.manga_manager.service.UserService;
import ru.alex.manga_manager.util.converter.UserConverter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    @Qualifier("defaultUserService")
    private final UserService userService;

    private final UserConverter<UserDto, User> userDtoConverter;

    @GetMapping("/")
    public UserDto findUserByPrincipal(Authentication authentication) {
        return userDtoConverter.convert(userService.findUserByAuthentication(authentication));
    }

}
