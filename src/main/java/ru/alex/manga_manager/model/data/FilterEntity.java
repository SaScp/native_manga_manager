package ru.alex.manga_manager.model.data;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data

public class FilterEntity  implements Serializable {
    private List<Long> types;

    private Integer pageNumber;

    private List<Long> genres;

    private String order;

    private Integer pageSize;

    public FilterEntity(List<String> types, String pageNumber, List<String> genres, String order, String pageSize) {
        this.types = types != null? types.stream().map(Long::parseLong).toList() : null;
        this.pageNumber = pageNumber != null? Integer.parseInt(pageNumber) : 0;
        this.genres = genres != null? genres.stream().map(Long::parseLong).toList() : null;
        this.order = order;
        this.pageSize = pageSize != null?  Integer.parseInt(pageSize) : 20;
    }
}
