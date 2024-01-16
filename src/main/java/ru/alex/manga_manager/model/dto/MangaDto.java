package ru.alex.manga_manager.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

@Data
public class MangaDto implements Serializable {

    @NotNull
    @NotEmpty
    @NotBlank
    private String id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String mainName;

    @NotNull
    @NotEmpty
    @NotBlank
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
    private String rusName;

    @NotNull
    @NotEmpty
    @NotBlank
    private String enName;

    private List<GenreDto> genres;

    private TypeDto type;

    private List<CommentDto> comment;
}