package ru.alex.manga_manager.model.data.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchEntity  implements Serializable {
    String title;
    Integer page;

    public SearchEntity(String title, String page) {
        this.title = title;
        this.page = page != null? Integer.parseInt(page) : 0;
    }
}
