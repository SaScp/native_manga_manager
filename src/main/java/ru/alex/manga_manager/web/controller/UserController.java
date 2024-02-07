package ru.alex.manga_manager.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.alex.manga_manager.model.dto.user.UserDto;
import ru.alex.manga_manager.service.UserService;
import ru.alex.manga_manager.util.converter.UserConverter;
import ru.alex.manga_manager.util.exception.ForbiddenException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    @Qualifier("defaultUserService")
    private final UserService userService;

    private final UserConverter userDtoConverter;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public UserDto findUserByAuthentication(Authentication authentication) {
        return userDtoConverter.convertFrom(userService.findUserByAuthentication(authentication));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/update-password")
    public HttpStatus update(@RequestBody UserDto userDto, Authentication authentication) {
        if (Optional.ofNullable(authentication).isPresent()) {
            userService.update(userDto, authentication);
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add/{id}")
    public HttpStatus add(@PathVariable String id, Authentication authentication) {
        return userService.add(id, Optional.ofNullable(authentication).orElseThrow(() ->
                new ForbiddenException("forbidden"))) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }
}
