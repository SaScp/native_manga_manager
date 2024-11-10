package ru.alex.manga_manager.service.filterhandler;

import ru.alex.manga_manager.model.data.entity.FilterEntity;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.repository.MangaRepository;

import java.util.List;

public class OrderIsEmptyHandler extends Handler {

    public OrderIsEmptyHandler(MangaRepository mangaRepository) {
        super(mangaRepository);
    }

    @Override
    public List<Manga> handleRequest(FilterEntity filterEntity) {
        if (filterEntity.getGenres() != null && filterEntity.getTypes() != null) {
           return this.mangaRepository.findAllByTypeInAndGenresIn(filterEntity.getTypes(), filterEntity.getGenres(), this.pageRequest);
        } else {
            if (order != null) {
               return this.mangaRepository.findAll(pageRequest).toList();
            } else {
                return nextHandler.handleRequest(filterEntity);
            }
        }
    }
}
