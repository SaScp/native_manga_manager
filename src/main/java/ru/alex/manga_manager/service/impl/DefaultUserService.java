package ru.alex.manga_manager.service.impl;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.data.user.Role;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UserDto;
import ru.alex.manga_manager.repository.MangaRepository;
import ru.alex.manga_manager.repository.RoleRepository;
import ru.alex.manga_manager.repository.UserRepository;
import ru.alex.manga_manager.service.UserService;
import ru.alex.manga_manager.util.converter.Converter;
import ru.alex.manga_manager.util.converter.UserConverter;

import ru.alex.manga_manager.util.exception.MangaNotFoundException;
import ru.alex.manga_manager.util.exception.RoleNotFoundException;
import ru.alex.manga_manager.util.exception.UserNotFoundException;

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

    private final UserConverter userConverter;

    @Qualifier("passwordEncoder")
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User save(UserDto userDto) {

        User user = this.userConverter.convertTo(userDto);
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
    @Cacheable(value = "findUserByAuthentication", key = "#authentication")
    @Transactional(readOnly = true)
    public User findUserByAuthentication(Authentication authentication) {
        return this.userRepository.findById(authentication.getName()).orElseThrow(() ->
                new UserNotFoundException("User" + authentication.getName() + "not found"));
    }

    @Override
    @Cacheable(value = "findByEmail", key = "#email")
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
    public void update(UserDto updateEntity, Authentication authentication) {

    }


}
