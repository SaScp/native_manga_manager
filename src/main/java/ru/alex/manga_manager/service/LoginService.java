package ru.alex.manga_manager.service;

import org.springframework.validation.BindingResult;
import ru.alex.manga_manager.model.dto.user.UserDto;
import ru.alex.manga_manager.model.response.Tokens;

public interface LoginService {
    Tokens login(UserDto userDto, BindingResult bindingResult);
}
