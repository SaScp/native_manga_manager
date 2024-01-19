package ru.alex.manga_manager.service;



import org.springframework.security.core.Authentication;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.RegistrationUserDto;

public interface UserService {
    User save(RegistrationUserDto userDto);

    User findUserByAuthentication(Authentication authentication);

    User findByEmail(String email);

    User findById(String id);

    boolean add(String id, Authentication authentication);

    boolean updatePassword(String oldPassword, String newPassword, Authentication authentication);
}
