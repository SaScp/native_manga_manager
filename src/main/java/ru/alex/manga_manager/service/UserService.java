package ru.alex.manga_manager.service;



import org.springframework.security.core.Authentication;
import ru.alex.manga_manager.model.data.User;
import ru.alex.manga_manager.model.dto.UserDto;

import java.security.Principal;

public interface UserService {
    User save(UserDto userDto);

    User findUserByAuthentication(Authentication authentication);

    User findByEmail(String email);
}
