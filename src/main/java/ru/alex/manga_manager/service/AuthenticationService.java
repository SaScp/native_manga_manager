package ru.alex.manga_manager.service;

import org.springframework.validation.BindingResult;
import ru.alex.manga_manager.model.dto.LoginUserDto;
import ru.alex.manga_manager.model.dto.RegistrationUserDto;
import ru.alex.manga_manager.model.dto.UserDto;
import ru.alex.manga_manager.model.response.Tokens;

public interface AuthenticationService {

    Tokens registration(RegistrationUserDto userDto, BindingResult bindingResult);

    Tokens login(LoginUserDto userDto, BindingResult bindingResult);
}
