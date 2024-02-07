package ru.alex.manga_manager.service.update.user;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UserDto;
import ru.alex.manga_manager.service.update.UpdateComponentStrategy;

import java.util.Optional;


public class PasswordUpdateComponent implements UpdateUserComponent {

    private final PasswordEncoder passwordEncoder;

    public PasswordUpdateComponent(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(UserDto component, User updateComponent) {
        if (Optional.ofNullable(component.getNewPassword()).isPresent() &&
        Optional.ofNullable(component.getPassword()).isPresent()) {
            if (passwordEncoder.matches(updateComponent.getPassword(), component.getPassword())) {
                updateComponent.setPassword(component.getNewPassword());
            } else {
                throw new BadCredentialsException("password is invalid");
            }
        }
    }
}
