package ru.alex.manga_manager.util.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UserDto;


@Component
@RequiredArgsConstructor
public class DefaultUserConverter implements UserConverter<User, UserDto> {

    private final ModelMapper modelMapper;
    @Override
    public User convert(UserDto convertDto) {
        return this.modelMapper.map(convertDto, User.class);
    }
}
