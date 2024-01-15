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
import ru.alex.manga_manager.util.exception.MangaNotFoundException;


import java.util.*;

@Service
@RequiredArgsConstructor
public class DefaultMangaService implements MangaService {

    private final MangaRepository mangaRepository;


    private PageRequest pageRequest;

    private List<Manga> mangas;

    private String order;

    private boolean orderFlag = true;

    @Override
    public Manga save(MangaDto mangaDto) {
        return null;
    }


    @Override
    public List<Manga> search(String title, Integer page) {
        this.pageRequest = PageRequest.of(page, 20);
        return mangaRepository.findByMainNameStartingWithOrSecondaryNameStartingWith(title, this.pageRequest);
    }

    public List<Manga> findAllMangas(Integer pageNumber,
                                     List<Long> genreIds,
                                     List<Long> types,
                                     String order,
                                     Integer pageSize
    ) {
        checkOrderOnStartsWithPlus(order);
        Sort sort = orderFlag ? Sort.by(this.order).descending() : Sort.by(this.order).ascending();
        this.pageRequest = order != null ? PageRequest.of(pageNumber, pageSize, sort) : PageRequest.of(pageNumber, pageSize);
        checkAllParams(order, types, genreIds);
        return mangas;
    }

    @Override
    public Manga findMangaById(String id) {
        return this.mangaRepository.findById(id)
                .orElseThrow(() -> new MangaNotFoundException("Manga with id: " + id + " Not Found"));
    }

    private void checkAllParams(String order,
                                List<Long> types,
                                List<Long> genreIds
    ) {
        if (genreIds != null && types != null && order != null) {
            mangas = this.mangaRepository.findAllByTypeInAndGenresIn(types, genreIds, pageRequest);
        } else {
            checkGenresOrTypeOrder(order, types, genreIds);
        }
    }

    private void checkGenresOrTypeOrder(String order,
                                        List<Long> types,
                                        List<Long> genreIds
    ) {
        if (genreIds != null && order != null) {
            mangas = this.mangaRepository.findAllByGenresIn(genreIds, pageRequest);
        } else if (types != null && order != null) {
            mangas = this.mangaRepository.findAllByTypesIn(types, pageRequest);
        } else {
            checkOrderIsEmpty(order, types, genreIds);
        }
    }

    private void checkOrderIsEmpty(String order,
                                   List<Long> types,
                                   List<Long> genreIds
    ) {
        if (genreIds != null && types != null) {
            mangas = this.mangaRepository.findAllByTypeInAndGenresIn(types, genreIds, this.pageRequest);
        } else {
            if (order != null) {
                mangas = this.mangaRepository.findAll(pageRequest).toList();
            } else {
                checkTypesAndGenresIds(order, types, genreIds);
            }
        }

    }

    private void checkTypesAndGenresIds(String order,
                                        List<Long> types,
                                        List<Long> genreIds
    ) {
        if (types != null) {
            mangas = this.mangaRepository.findAllByTypesIn(types, this.pageRequest);
        } else {
            if (genreIds != null) {
                mangas = this.mangaRepository.findAllByGenresIn(genreIds, this.pageRequest);
            } else {
                mangas = this.mangaRepository.findAll(this.pageRequest).toList();
            }
        }
    }

    private void checkOrderOnStartsWithPlus(String order) {
        if (order != null) {
            this.orderFlag = order.startsWith(" ");
            this.order = order.substring(1);
        }
    }


}
