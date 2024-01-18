package ru.alex.manga_manager.util.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alex.manga_manager.model.dto.RegistrationUserDto;
import ru.alex.manga_manager.model.dto.UserDto;
import ru.alex.manga_manager.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationUserDto userDto = (RegistrationUserDto) target;


        if (this.userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            errors.rejectValue("email", "507", "The user with this email already exists");
        }
    }
}
