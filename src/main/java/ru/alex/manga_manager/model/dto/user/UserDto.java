package ru.alex.manga_manager.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.alex.manga_manager.model.dto.manga.MangaDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class UserDto implements  Serializable {

    private String id;

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String oldPassword;

    @NotNull
    @NotEmpty
    @NotBlank
    @JsonProperty(value = "new-password", access = JsonProperty.Access.WRITE_ONLY)
    private String newPassword;

    @NotNull
    @NotEmpty
    @NotBlank
    private String username;

    @NotNull
    @NotEmpty
    @NotBlank
    private String fullName;


    @NotNull
    @NotEmpty
    @NotBlank
    private Date dateOfBirth;


    private List<MangaDto> mangas;

}