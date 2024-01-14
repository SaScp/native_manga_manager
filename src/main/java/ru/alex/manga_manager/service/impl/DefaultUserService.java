package ru.alex.manga_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.alex.manga_manager.model.data.Role;
import ru.alex.manga_manager.model.data.User;
import ru.alex.manga_manager.model.dto.UserDto;
import ru.alex.manga_manager.repository.RoleRepository;
import ru.alex.manga_manager.repository.UserRepository;
import ru.alex.manga_manager.service.UserService;
import ru.alex.manga_manager.util.converter.UserConverter;
import ru.alex.manga_manager.util.exception.RoleNotFoundException;
import ru.alex.manga_manager.util.exception.UserNotFoundException;

import java.security.Principal;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Qualifier("defaultUserConverter")
    private final UserConverter<User, UserDto> userConverter;

    @Qualifier("passwordEncoder")
    private final PasswordEncoder passwordEncoder;

    public User save(UserDto userDto) {

        User user = this.userConverter.convert(userDto);
        Role role = this.roleRepository.findById(1L).orElseThrow(() -> new RoleNotFoundException("Role Not Found"));

        String id = UUID.randomUUID().toString();
        if (this.userRepository.findById(id).isPresent())
            id = UUID.randomUUID().toString();

        role.add(user);

        user.setId(id);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(role));
        user.setRegistrationDate(Date.from(Instant.now()));
        user.setDateOfBirth(Date.from(Instant.now()));

        user = this.userRepository.save(user);
        this.roleRepository.save(role);
        return user;
    }

    @Override
    public User findUserByPrincipal(Principal principal) {
        return this.userRepository.findByEmail(principal.getName()).orElseThrow(() ->
                new UserNotFoundException("User" + principal.getName() + "not found"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("user with email:" + email+ "not found"));
    }


}
