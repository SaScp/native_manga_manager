package ru.alex.manga_manager.model.dto.manga;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.alex.manga_manager.model.dto.comment.CommentDto;
import ru.alex.manga_manager.model.dto.group.ViewMangaJsonGroup;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Schema(description = "Манга")
public class MangaDto implements Serializable {

    @JsonView(value = {ViewMangaJsonGroup.CatalogDataTitle.class, ViewMangaJsonGroup.InnerDataTitle.class, ViewMangaJsonGroup.SearchDataTitle.class})
    private String id;


    @Schema(description = "Основное имя манги")
    @JsonView(value = {ViewMangaJsonGroup.InnerDataTitle.class})
    private String mainName;


    @Schema(description = "Втрочичное")
    @JsonView(value = {ViewMangaJsonGroup.InnerDataTitle.class})
    private String secondaryName;


    @JsonView(value = {ViewMangaJsonGroup.InnerDataTitle.class})
    private Integer issueYear;

    @JsonView(value = {ViewMangaJsonGroup.InnerDataTitle.class})
    private Double avgRating;

    @JsonView(value = {ViewMangaJsonGroup.InnerDataTitle.class})
    private Boolean isYaoi;

    @JsonView(value = {ViewMangaJsonGroup.InnerDataTitle.class})
    private Boolean isErotic;

    @JsonView(value = {ViewMangaJsonGroup.CatalogDataTitle.class, ViewMangaJsonGroup.InnerDataTitle.class})
    private String img;

    @Schema(description = "имя на русском")
    @JsonView(value = {ViewMangaJsonGroup.CatalogDataTitle.class, ViewMangaJsonGroup.InnerDataTitle.class , ViewMangaJsonGroup.SearchDataTitle.class})
    private String rusName;

    @Schema(description = "имя на английском")
    @JsonView(value = {ViewMangaJsonGroup.InnerDataTitle.class, ViewMangaJsonGroup.SearchDataTitle.class})
    private String enName;

    @Schema(description = "Жанр")
    @JsonView(value = {ViewMangaJsonGroup.InnerDataTitle.class})
    private List<GenreDto> genres;

    @Schema(description = "Тип")
    @JsonView(value = {ViewMangaJsonGroup.CatalogDataTitle.class, ViewMangaJsonGroup.InnerDataTitle.class , ViewMangaJsonGroup.SearchDataTitle.class})
    private TypeDto type;

}