package ru.alex.manga_manager.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Сущность пользователя")
public class UserDto implements  Serializable {

    private String id;

    @Email
    @Schema(description = "почта", example = "testemail@gmail.com")
    private String email;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Hidden
    private String password;


    @JsonProperty(value = "new-password", access = JsonProperty.Access.WRITE_ONLY)
    @Hidden
    private String newPassword;

    @Schema(description = "пользовательское имя", example = "test-user")
    private String username;

    @Schema(description = "имя", example = "Jon")
    private String fullName;

    @Schema(description = "дата рождения")
    private Date dateOfBirth;

    @Schema(description = "манги в коллекции пользователя")
    private List<MangaDto> mangas;

}