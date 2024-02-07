package ru.alex.manga_manager.service;



import org.springframework.security.core.Authentication;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UserDto;

public interface UserService {
    User save(UserDto userDto);

    User findUserByAuthentication(Authentication authentication);

    User findByEmail(String email);

    User findById(String id);

    boolean add(String id, Authentication authentication);

   void update(UserDto updateEntity, Authentication authentication);
}
