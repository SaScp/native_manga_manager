package ru.alex.manga_manager.service.filterhandler;

import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.entity.FilterEntity;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.repository.MangaRepository;

import java.util.List;

@Component
public class AllParametersHandler extends Handler {


    public AllParametersHandler(MangaRepository mangaRepository) {
        super(mangaRepository);
    }

    @Override
    public List<Manga> handleRequest(FilterEntity filterEntity) {
        if (filterEntity.getGenres() != null && filterEntity.getTypes() != null && order != null) {
            return this.mangaRepository.findAllByTypeInAndGenresIn(filterEntity.getTypes(), filterEntity.getGenres(), pageRequest);
        } else {
            return nextHandler.handleRequest(filterEntity);
        }
    }
}
