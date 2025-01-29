package ru.alex.manga_manager.web.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.alex.manga_manager.model.dto.manga.MangaDto;
import ru.alex.manga_manager.model.dto.user.UserDto;
import ru.alex.manga_manager.service.UserService;
import ru.alex.manga_manager.util.exception.ForbiddenException;
import ru.alex.manga_manager.util.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "UserController", description = "Контроллер для взаимодействия с данными пользователя")
public class UserController {

    @Qualifier("defaultUserService")
    private final UserService userService;

    @GetMapping("/")
    public UserDto findUserByAuthentication(@Schema(hidden = true) Authentication authentication) {
        return UserMapper.INSTANCE.userToUserDto(userService.findUserByAuthentication(authentication));
    }

    @Operation(
            responses = {
                    @ApiResponse(responseCode = "200", description = "Корректно выполненное обновление"),
                    @ApiResponse(responseCode = "400", description = "Введены некорректные данные"),
                    @ApiResponse(responseCode = "401", description = "Введен некорректный пароль")
            },
            summary = "Обновление данных пользователя",
            description = "позволяет обновить данные пользователя"
    )
    @PatchMapping("/update")
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
            description = "позволяет добавить новую мангу в коллекцию пользователя",
            parameters = {
                    @Parameter(name = "title_id",
                            required = true,
                            allowEmptyValue = true)},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Манга добавлен успешно"),
                    @ApiResponse(responseCode = "403", description = "Доступ закрыт")
            }
    )
    @PostMapping("/add/")
    public ResponseEntity<Void> add(@RequestParam("title_id") @Parameter(description = "ID манги") String id,
                                    Authentication authentication) {
        userService.add(id, authentication);
        return ResponseEntity.ok().build();
    }



}
