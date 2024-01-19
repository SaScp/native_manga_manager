package ru.alex.manga_manager.model.dto.manga;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.alex.manga_manager.model.data.manga.Genre;


/**
 * DTO for {@link Genre}
 */
@Data
public class GenreDto{
    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String genre;
}