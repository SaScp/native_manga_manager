package ru.alex.manga_manager.model.dto.manga;

import lombok.Data;

import java.util.UUID;

@Data
public class ChapterDto {
    private UUID id;

    private String url;
}
