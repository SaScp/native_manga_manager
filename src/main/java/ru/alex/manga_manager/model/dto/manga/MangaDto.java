package ru.alex.manga_manager.model.dto.manga;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

@Data
@Schema(description = "Манга")
public class MangaDto implements Serializable {

    @NotNull
    @NotEmpty
    @NotBlank
    private String id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "Основное имя манги")
    private String mainName;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "Втрочичное")
    private String secondaryName;

    @NotNull
    private Integer issueYear;

    @NotNull
    private Double avgRating;

    @NotNull
    private Boolean isYaoi;

    @NotNull
    private Boolean isErotic;

    private String img;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "имя на русском")
    private String rusName;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "имя на английском")
    private String enName;

    @Schema(description = "Жанр")
    private List<GenreDto> genres;

    @Schema(description = "Тип")
    private TypeDto type;

}