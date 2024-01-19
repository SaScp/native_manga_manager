package ru.alex.manga_manager.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateUserDto {

    private String email;

    private String oldPassword;

    private String newPassword;

    private String username;

    private String fullName;

    private Date dateOfBirth;
}
