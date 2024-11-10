package ru.alex.manga_manager.model.data.entity;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FilterEntity  implements Serializable {

    @Schema(description = "типы")
    protected List<Long> types;

    @Schema(description = "количество страниц")
    private Integer pageNumber;

    @Schema(description = "жанры")
    protected List<Long> genres;

    @Schema(description = "сортируемое поле")
    protected String order;

    @Schema(description = "размер страницы")
    private Integer pageSize;

    public FilterEntity(List<String> types, String pageNumber, List<String> genres, String order, String pageSize) {
        this.types = types != null? types.stream().map(Long::parseLong).toList() : null;
        this.pageNumber = pageNumber != null? Integer.parseInt(pageNumber) : 0;
        this.genres = genres != null? genres.stream().map(Long::parseLong).toList() : null;
        this.order = order;
        this.pageSize = pageSize != null?  Integer.parseInt(pageSize) : 20;
    }
    public FilterEntity(){}
}
