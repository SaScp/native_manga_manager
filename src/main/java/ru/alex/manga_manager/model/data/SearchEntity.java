package ru.alex.manga_manager.model.data;

import lombok.Data;

@Data
public class SearchEntity {
    String title;
    Integer page;

    public SearchEntity(String title, String page) {
        this.title = title;
        this.page = page != null? Integer.parseInt(page) : 0;
    }
}
