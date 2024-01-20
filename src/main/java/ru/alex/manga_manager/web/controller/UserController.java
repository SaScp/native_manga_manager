package ru.alex.manga_manager.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UpdateUserDto;
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

    private final UserConverter<UserDto, User> userDtoConverter;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public UserDto findUserByAuthentication(Optional<Authentication> authentication) {
        return userDtoConverter.convert(userService.findUserByAuthentication( authentication.orElseThrow(()
                -> new ForbiddenException("forbidden"))));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/update-password")
    public HttpStatus update(@RequestBody UpdateUserDto userDto,
                             Optional<Authentication> authentication) {
        if (authentication.isPresent() &&
                userService.updatePassword(userDto.getOldPassword(), userDto.getNewPassword(), authentication.get())) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add/{id}")
    public HttpStatus add(@PathVariable String id, Optional<Authentication> authentication) {
        return userService.add(id, authentication.orElseThrow(() ->
                new ForbiddenException("forbidden"))) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }
}
