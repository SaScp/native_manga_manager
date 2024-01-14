package ru.alex.manga_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.alex.manga_manager.model.data.Manga;
import ru.alex.manga_manager.model.dto.MangaDto;
import ru.alex.manga_manager.repository.MangaRepository;
import ru.alex.manga_manager.service.MangaService;


import java.util.*;

@Service
@RequiredArgsConstructor
public class DefaultMangaService implements MangaService {

    private final MangaRepository mangaRepository;


    private PageRequest pageRequest;

    private List<Manga> mangas;

    @Override
    public Manga save(MangaDto mangaDto) {
        return null;
    }


    @Override
    public List<Manga> search(String title, Integer page) {
        this.pageRequest = PageRequest.of(page, 20);
        return mangaRepository.findByMainNameStartingWithOrSecondaryNameStartingWith(title, this.pageRequest);
    }

    public List<Manga> findAllMangas(
            Integer pageNumber,
            List<Long> genreIds,
            List<Long> types,
            String order
    ) {
        this.pageRequest = PageRequest.of(pageNumber, 20);
        if (types != null) {
            mangas = this.mangaRepository.findAllByTypesIn(types, this.pageRequest);
        } else {
            if (genreIds != null) {
                mangas = this.mangaRepository.findByGenresIn(genreIds, this.pageRequest);
            } else {
                checkOrderIsEmpty(order, genreIds, types);
            }
        }
        return mangas;
    }

    private void checkOrderIsEmpty(
            String order,
            List<Long> genreIds,
            List<Long> types
    ) {
        if (order != null) {
            Sort.sort(Manga.class);
            mangas = this.mangaRepository.findAll(Sort.by(order));
        } else {
            if (genreIds != null && types != null) {
                mangas = this.mangaRepository.findByTypeInAndGenresIn(types, genreIds, this.pageRequest);
            } else {
                mangas = this.mangaRepository.findAll(this.pageRequest).toList();
            }
        }
    }

}
