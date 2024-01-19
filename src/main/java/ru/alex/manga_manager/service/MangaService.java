package ru.alex.manga_manager.service;

import ru.alex.manga_manager.model.data.entity.FilterEntity;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.data.entity.SearchEntity;
import ru.alex.manga_manager.model.dto.manga.MangaDto;

import java.util.List;

public interface MangaService {
    Manga save(MangaDto mangaDto);

    List<Manga> search(SearchEntity search);
    List<Manga> findAllMangas(FilterEntity filterEntity);

    Manga findMangaById(String id);


}
