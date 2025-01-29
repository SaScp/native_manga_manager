package ru.alex.manga_manager.service.impl;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
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
import ru.alex.manga_manager.service.factory.CreateFactory;
import ru.alex.manga_manager.service.update.user.FullNameUpdateComponent;
import ru.alex.manga_manager.service.update.user.PasswordUpdateComponent;
import ru.alex.manga_manager.service.update.user.UpdateUserComponent;
import ru.alex.manga_manager.service.update.user.UsernameUpdateComponent;

import ru.alex.manga_manager.util.exception.ForbiddenException;
import ru.alex.manga_manager.util.exception.MangaNotFoundException;
import ru.alex.manga_manager.util.exception.RoleNotFoundException;
import ru.alex.manga_manager.util.exception.UserNotFoundException;
import ru.alex.manga_manager.util.mapper.UserMapper;

import java.time.Instant;
import java.util.*;

@Slf4j
@Service
@Order
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final MangaRepository mangaRepository;


    @Qualifier("passwordEncoder")
    private final PasswordEncoder passwordEncoder;

    private final CreateFactory<User, UserDto> userCreateFactory;

    @Override
    @Transactional
    public User save(UserDto userDto) {
        Role role = this.roleRepository.findById(1L).orElseThrow(() -> new RoleNotFoundException("Role Not Found"));

        User user = userCreateFactory.create(userDto);

        user.addRole(role);
        this.userRepository.save(user);
        this.roleRepository.save(role);
        return user;
    }

    @Override
    @Cacheable(value = "DefaultUserService::findUserByAuthentication", key = "#authentication.name")
    public User findUserByAuthentication(Authentication authentication) {
        return this.userRepository.findById(authentication.getName()).orElseThrow(() ->
                new UserNotFoundException("User " + authentication.getName() + " not found"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("user with email: " + email + " not found"));
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("user with id: " + id + " not found"));
    }

    @Transactional
    @CachePut(value = "DefaultUserService::findUserByAuthentication", key = "#authentication.name")
    public User add(String id, Authentication authentication) {
        Optional.ofNullable(authentication).orElseThrow(() ->
                new ForbiddenException("forbidden"));
        Manga manga = mangaRepository.findById(id).orElseThrow(() ->
                new MangaNotFoundException("Manga " + id + " Not Found"));
        User user = findUserByAuthentication(authentication);
        user.addManga(manga);

        mangaRepository.save(manga);
        return user;
    }

    @Override
    @Transactional
    @CachePut(value = "DefaultUserService::findUserByAuthentication", key = "#authentication.name")
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
