package ru.alex.manga_manager.service;



import ru.alex.manga_manager.model.data.User;
import ru.alex.manga_manager.model.dto.UserDto;

import java.security.Principal;

public interface UserService {
    User save(UserDto userDto);

    User findUserByPrincipal(Principal principal);

    User findByEmail(String email);
}
