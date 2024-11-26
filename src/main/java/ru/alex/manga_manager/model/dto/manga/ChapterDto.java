package ru.alex.manga_manager.model.dto.manga;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
public class ChapterDto implements Serializable {
    private UUID id;

    private Set<String> urls;
}
