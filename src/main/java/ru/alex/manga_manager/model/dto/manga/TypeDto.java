package ru.alex.manga_manager.model.dto.manga;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.alex.manga_manager.model.dto.group.ViewMangaJsonGroup;

@Data
public class TypeDto {

    @NotNull
    @JsonView(value = {ViewMangaJsonGroup.CatalogDataTitle.class, ViewMangaJsonGroup.InnerDataTitle.class , ViewMangaJsonGroup.SearchDataTitle.class})
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @JsonView(value = {ViewMangaJsonGroup.CatalogDataTitle.class, ViewMangaJsonGroup.InnerDataTitle.class , ViewMangaJsonGroup.SearchDataTitle.class})
    private String type;

}
