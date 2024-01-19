package ru.alex.manga_manager.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alex.manga_manager.model.dto.user.RegistrationUserDto;

public class PasswordValidator implements Validator {
    private final static String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";


    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationUserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final var user = (RegistrationUserDto) target;

        if (!user.getPassword().matches(passwordRegex)) {
            errors.rejectValue("password", "500", "password is invalid");
        }
    }
}
