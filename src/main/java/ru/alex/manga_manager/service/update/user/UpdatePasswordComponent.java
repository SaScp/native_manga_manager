package ru.alex.manga_manager.service.update.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UserDto;
import ru.alex.manga_manager.service.update.UpdateComponentStrategy;


public class UpdatePasswordComponent implements UpdateComponentStrategy<UserDto, User> {

    private final PasswordEncoder passwordEncoder;

    public UpdatePasswordComponent(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(UserDto component, User updateComponent) {

    }
}
