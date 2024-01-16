package ru.alex.manga_manager.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class UserDto implements Serializable {

    private String id;

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    private String password;

    @NotNull
    @NotEmpty
    @NotBlank
   private String username;

    @NotNull
    @NotEmpty
    @NotBlank
    private String fullName;

    /*@NotNull*/
    private Date dateOfBirth;

    private List<MangaDto> mangas;

}