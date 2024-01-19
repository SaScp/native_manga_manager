package ru.alex.manga_manager.model.dto.manga;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TypeDto {
    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String type;

}
