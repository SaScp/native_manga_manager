package ru.alex.manga_manager.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginUserDto {

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    private String password;

}
