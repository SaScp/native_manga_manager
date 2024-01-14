package ru.alex.manga_manager.service;

import ru.alex.manga_manager.model.data.Manga;
import ru.alex.manga_manager.model.dto.MangaDto;

import java.util.List;

public interface MangaService {
    Manga save(MangaDto mangaDto);

    List<Manga> search(String title, Integer page);
    List<Manga> findAllMangas(Integer pageNumber,
                              List<Long> genresIds,
                              List<Long> types,
                              String order
    );
}
