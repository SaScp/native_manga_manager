package ru.alex.manga_manager.service.update.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UpdateUserDto;

import java.util.Optional;


public class UpdatePasswordComponent implements UserUpdateComponent {

    private final PasswordEncoder passwordEncoder;

    public UpdatePasswordComponent(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(String component, User updateComponent) {
        if (Optional.ofNullable(component).isPresent()) {
            updateComponent.setPassword(passwordEncoder.encode(component));
        }
    }
}
