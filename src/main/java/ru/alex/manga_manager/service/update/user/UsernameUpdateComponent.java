package ru.alex.manga_manager.service.update.user;

import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UserDto;

import java.util.Optional;

public class UsernameUpdateComponent implements UpdateUserComponent {
    @Override
    public void execute(UserDto component, User updateComponent) {
        if (Optional.ofNullable(component.getUsername()).isPresent()) {
            updateComponent.setFullName(component.getUsername());
        }
    }
}
