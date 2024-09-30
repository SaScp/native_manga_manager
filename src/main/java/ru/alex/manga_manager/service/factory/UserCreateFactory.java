package ru.alex.manga_manager.service.factory;

import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UserDto;

@Component
public class UserCreateFactory implements CreateFactory<User, UserDto>{
    @Override
    public User create(UserDto entity) {
        return null;
    }
}
