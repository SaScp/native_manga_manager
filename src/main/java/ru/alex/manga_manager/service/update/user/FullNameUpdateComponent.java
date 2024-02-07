package ru.alex.manga_manager.service.update.user;

import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UserDto;
import ru.alex.manga_manager.service.update.UpdateComponentStrategy;


import java.util.Optional;

public class FullNameUpdateComponent implements UpdateUserComponent {
    @Override
    public void execute(UserDto updateEntity, User entity) {
        if (Optional.ofNullable(updateEntity.getFullName()).isPresent()) {
            entity.setFullName(updateEntity.getFullName());
        }
    }
}
