package ru.alex.manga_manager.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alex.manga_manager.model.dto.user.RegistrationUserDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailValidator implements Validator {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationUserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RegistrationUserDto user = (RegistrationUserDto) target;
        if (isValidEmail(user.getEmail())) {
            errors.rejectValue("email", "500", "email is invalid");
        }
    }


    private boolean isValidEmail(String email) {
        if (email == null) {
            return true;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return !matcher.matches();
    }
}
