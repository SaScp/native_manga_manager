package ru.alex.manga_manager.service.impl;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
import ru.alex.manga_manager.service.update.user.FullNameUpdateComponent;
import ru.alex.manga_manager.service.update.user.PasswordUpdateComponent;
import ru.alex.manga_manager.service.update.user.UpdateUserComponent;
import ru.alex.manga_manager.service.update.user.UsernameUpdateComponent;
import ru.alex.manga_manager.util.converter.Converter;
import ru.alex.manga_manager.util.converter.UserConverter;

import ru.alex.manga_manager.util.exception.MangaNotFoundException;
import ru.alex.manga_manager.util.exception.RoleNotFoundException;
import ru.alex.manga_manager.util.exception.UserNotFoundException;

import java.time.Instant;
import java.util.*;

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
    @Cacheable(value = "DefaultUserService::findUserByAuthentication", key = "#authentication.name")
    @Transactional(readOnly = true)
    public User findUserByAuthentication(Authentication authentication) {
        return this.userRepository.findByEmail(authentication.getName()).orElseThrow(() ->
                new UserNotFoundException("User " + authentication.getName() + " not found"));
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
    @CachePut(value = "DefaultUserService::findUserByAuthentication", key = "#authentication.name")
    public User add(String id, Authentication authentication) {
        Manga manga = mangaRepository.findById(id).orElseThrow(() ->
                new MangaNotFoundException("Manga " + id + " Not Found"));
        User user = findUserByAuthentication(authentication);
        user.addManga(manga);
        manga.addUser(user);

        userRepository.save(user);
        mangaRepository.save(manga);
        return user;
    }

    @Override

    @CachePut(value = "DefaultUserService::findUserByAuthentication", key = "#authentication.name")
    @Transactional
    public User update(UserDto updateEntity, Authentication authentication) {
        User user = findUserByAuthentication(authentication);
        List<UpdateUserComponent> components = List.of(new FullNameUpdateComponent(),
                new PasswordUpdateComponent(passwordEncoder), new UsernameUpdateComponent());

        for (var i : components) {
            i.execute(updateEntity, user);
        }
        return userRepository.save(user);
    }


}
