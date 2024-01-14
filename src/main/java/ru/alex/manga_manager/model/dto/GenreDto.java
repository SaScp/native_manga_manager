package ru.alex.manga_manager.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * DTO for {@link ru.alex.manga_manager.model.data.Genre}
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