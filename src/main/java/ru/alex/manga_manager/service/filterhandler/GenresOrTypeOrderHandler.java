package ru.alex.manga_manager.service.filterhandler;

import ru.alex.manga_manager.model.data.entity.FilterEntity;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.repository.MangaRepository;

import java.util.List;

public class GenresOrTypeOrderHandler extends Handler{

    public GenresOrTypeOrderHandler(MangaRepository mangaRepository) {
        super(mangaRepository);
    }

    @Override
    public List<Manga> handleRequest(FilterEntity filterEntity) {
        if (filterEntity.getGenres() != null && order != null) {
           return this.mangaRepository.findAllByGenresIn(filterEntity.getGenres(), pageRequest);
        } else if (filterEntity.getTypes() != null && order != null) {
            return this.mangaRepository.findAllByTypesIn(filterEntity.getTypes(), pageRequest);
        } else {
           return nextHandler.handleRequest(filterEntity);
        }
    }
}
