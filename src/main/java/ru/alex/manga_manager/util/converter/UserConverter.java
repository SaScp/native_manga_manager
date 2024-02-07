package ru.alex.manga_manager.util.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UserDto;

@Component
public class UserConverter implements Converter<User, UserDto> {
    private final ModelMapper modelMapper;

    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public User convertTo(UserDto convertEntity) {
        return modelMapper.map(convertEntity, User.class);
    }

    @Override
    public UserDto convertFrom(User convertEntity) {
        return modelMapper.map(convertEntity, UserDto.class);
    }
}
