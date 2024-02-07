package ru.alex.manga_manager.util.converter;

import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UserDto;

@Component
public class UserConverter implements ConverterStrategy<User, UserDto>{

    @Override
    public User convertTo(UserDto convertEntity) {
        return null;
    }

    @Override
    public UserDto convertFrom(User convertEntity) {
        return null;
    }
}
