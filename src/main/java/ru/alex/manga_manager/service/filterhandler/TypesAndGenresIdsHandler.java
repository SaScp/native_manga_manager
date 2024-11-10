package ru.alex.manga_manager.service.filterhandler;

import ru.alex.manga_manager.model.data.entity.FilterEntity;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.repository.MangaRepository;

import java.util.List;

public class TypesAndGenresIdsHandler extends Handler{

    public TypesAndGenresIdsHandler(MangaRepository mangaRepository) {
        super(mangaRepository);
    }

    @Override
    public List<Manga> handleRequest(FilterEntity filterEntity) {
        if (filterEntity.getTypes() != null) {
            return   this.mangaRepository.findAllByTypesIn(filterEntity.getTypes(), this.pageRequest);
        } else {
            if (filterEntity.getGenres() != null) {
                return this.mangaRepository.findAllByGenresIn(filterEntity.getGenres(), this.pageRequest);
            } else {
                return this.mangaRepository.findAll(this.pageRequest).toList();
            }
        }
    }
}
