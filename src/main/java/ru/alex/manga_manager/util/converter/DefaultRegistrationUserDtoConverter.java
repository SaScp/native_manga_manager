package ru.alex.manga_manager.util.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.User;
import ru.alex.manga_manager.model.dto.RegistrationUserDto;
import ru.alex.manga_manager.model.dto.UserDto;

@Component
@RequiredArgsConstructor
public class DefaultRegistrationUserDtoConverter implements UserConverter<User, RegistrationUserDto>{
    private final ModelMapper modelMapper;

    @Override
    public User convert(RegistrationUserDto convertDto) {
        return modelMapper.map(convertDto, User.class);
    }
}
