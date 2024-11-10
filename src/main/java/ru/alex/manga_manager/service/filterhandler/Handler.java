package ru.alex.manga_manager.service.filterhandler;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import ru.alex.manga_manager.model.data.entity.FilterEntity;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.repository.MangaRepository;

import java.util.List;

@Setter
public abstract class Handler {

    protected Handler nextHandler;


    protected static PageRequest pageRequest;


    protected boolean orderFlag = true;

    protected String order;

    protected MangaRepository mangaRepository;

    public Handler(MangaRepository mangaRepository) {
        this.mangaRepository = mangaRepository;
    }

    public abstract List<Manga> handleRequest(FilterEntity filterEntity);
}
