package ru.alex.manga_manager.util.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.User;
import ru.alex.manga_manager.model.dto.UserDto;

@Component
@RequiredArgsConstructor
public class DefaultUserDtoConvertor implements UserConverter<UserDto, User> {

    private final ModelMapper modelMapper;

    @Override
    public UserDto convert(User convertDto) {
        return modelMapper.map(convertDto, UserDto.class);
    }
}
