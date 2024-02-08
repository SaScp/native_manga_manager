package ru.alex.manga_manager.web.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Tag(name = "UserController", description = "Контроллер для взаимодействия с данными пользователя")
public class UserController {

    @Qualifier("defaultUserService")
    private final UserService userService;

    private final UserConverter userDtoConverter;

    @Hidden
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public UserDto findUserByAuthentication(Authentication authentication) {
        return userDtoConverter.convertFrom(userService.findUserByAuthentication(authentication));
    }

    @Operation(
            summary = "Обновление пароля пользователя",
            description = "позволяет обновить пароль пользователя"
    )
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

    @Operation(
            summary = "Добавление новых манг",
            description = "позволяет добавить новую мангу в коллекцию пользователя"
    )
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add/{id}")
    public ResponseEntity<Void> add(@PathVariable @Parameter(description = "ID пользователя") String id, Authentication authentication) {
        userService.add(id, Optional.ofNullable(authentication).orElseThrow(() ->
                new ForbiddenException("forbidden")));
        return ResponseEntity.ok().build();
    }
}
