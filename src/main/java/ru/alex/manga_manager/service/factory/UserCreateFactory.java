package ru.alex.manga_manager.service.factory;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UserDto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Component
public class UserCreateFactory implements CreateFactory<User, UserDto>{

    private final PasswordEncoder passwordEncoder;

    public UserCreateFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(UserDto entity) {
        return User.builder()
                .password(this.passwordEncoder.encode(entity.getPassword()))
                .registrationDate(Date.from(Instant.now()))
                .dateOfBirth(entity.getDateOfBirth())
                .fullName(entity.getFullName())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .build();
    }
}
