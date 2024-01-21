package ru.alex.manga_manager.service.impl;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.data.user.Role;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.RegistrationUserDto;
import ru.alex.manga_manager.model.dto.user.UpdateUserDto;
import ru.alex.manga_manager.repository.MangaRepository;
import ru.alex.manga_manager.repository.RoleRepository;
import ru.alex.manga_manager.repository.UserRepository;
import ru.alex.manga_manager.service.UserService;
import ru.alex.manga_manager.service.update.user.UpdatePasswordComponent;
import ru.alex.manga_manager.util.converter.UserConverter;
import ru.alex.manga_manager.util.exception.ForbiddenException;
import ru.alex.manga_manager.util.exception.MangaNotFoundException;
import ru.alex.manga_manager.util.exception.RoleNotFoundException;
import ru.alex.manga_manager.util.exception.UserNotFoundException;
import ru.alex.manga_manager.util.validator.PasswordValidator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final MangaRepository mangaRepository;

    @Qualifier("defaultRegistrationUserDtoConverter")
    private final UserConverter<User, RegistrationUserDto> userConverter;

    @Qualifier("passwordEncoder")
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User save(RegistrationUserDto userDto) {

        User user = this.userConverter.convert(userDto);
        Role role = this.roleRepository.findById(1L).orElseThrow(() -> new RoleNotFoundException("Role Not Found"));

        String id = UUID.randomUUID().toString();
        if (this.userRepository.findById(id).isPresent())
            id = UUID.randomUUID().toString();


        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(Date.from(Instant.now()));
        user.setDateOfBirth(Date.from(Instant.now()));
        user.setMangas(new ArrayList<>());
        user.setRoles(Set.of(role));
        user.setId(id);

        role.add(user);
        user = this.userRepository.save(user);
        this.roleRepository.save(role);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByAuthentication(Authentication authentication) {
        return this.userRepository.findById(authentication.getName()).orElseThrow(() ->
                new UserNotFoundException("User" + authentication.getName() + "not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("user with email:" + email + "not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("user with id:" + id + "not found"));
    }

    @Transactional
    public boolean add(String id, Authentication authentication) {
            Manga manga = mangaRepository.findById(id).orElseThrow(() ->
                    new MangaNotFoundException("Manga " + id + " Not Found"));
            User user = findUserByAuthentication(authentication);
            user.addManga(manga);
            manga.addUser(user);
            try {
                userRepository.save(user);
                mangaRepository.save(manga);
                return true;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return false;
            }
    }

    @Override
    @Transactional
    public boolean updatePassword(UpdateUserDto userDto, Authentication authentication) {
        User userByAuthentication = findUserByAuthentication(authentication);
        if (passwordEncoder.matches(userByAuthentication.getPassword(), userDto.getOldPassword())) {
            try {
                UpdatePasswordComponent update = new UpdatePasswordComponent(passwordEncoder);
                update.execute(userDto, userByAuthentication);
                userRepository.save(userByAuthentication);
                return true;
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return false;
            }
        }
        return false;
    }

}
