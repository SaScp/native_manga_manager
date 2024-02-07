package ru.alex.manga_manager.service.update.user;

import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.user.UserDto;
import ru.alex.manga_manager.service.update.UpdateComponentStrategy;

public interface UpdateUserComponent extends UpdateComponentStrategy<UserDto, User> {
}
