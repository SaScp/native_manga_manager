package ru.alex.manga_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.manga_manager.model.data.FilterEntity;
import ru.alex.manga_manager.model.data.Manga;
import ru.alex.manga_manager.model.data.SearchEntity;
import ru.alex.manga_manager.model.dto.MangaDto;
import ru.alex.manga_manager.repository.MangaRepository;
import ru.alex.manga_manager.service.MangaService;
import ru.alex.manga_manager.util.converter.MangaConverter;
import ru.alex.manga_manager.util.exception.MangaNotFoundException;


import java.util.*;

@Service
@RequiredArgsConstructor
public class DefaultMangaService implements MangaService {

    private final MangaRepository mangaRepository;


    private final MangaConverter<Manga, MangaDto> mangaConverter;

    private PageRequest pageRequest;

    private List<Manga> mangas;

    private String order;

    private boolean orderFlag = true;



    @Override
    public Manga save(MangaDto mangaDto) {
        Manga manga = mangaConverter.convert(mangaDto);
        mangaDto.setId(UUID.randomUUID().toString());
        mangaRepository.save(manga);
        return manga;
    }

    @Override
    @Cacheable(value = "MangaService::search", key = "#search")
    public List<Manga> search(SearchEntity search) {
        this.pageRequest = PageRequest.of(search.getPage(), 20);
        return mangaRepository.findByMainNameStartingWithOrSecondaryNameStartingWith(search.getTitle(), this.pageRequest);
    }

    @Override
    @Transactional
    @Cacheable(value = "MangaService::findAllMangas", key = "#filterEntity")
    public List<Manga> findAllMangas(FilterEntity filterEntity) {
        checkOrderOnStartsWithPlus(order);
        if (order != null) {
            Sort sort = orderFlag ? Sort.by(this.order).descending() : Sort.by(this.order).ascending();
            this.pageRequest = PageRequest.of(filterEntity.getPageNumber(),filterEntity.getPageSize(), sort);
        } else {
            this.pageRequest = PageRequest.of(filterEntity.getPageNumber(),filterEntity.getPageSize());
        }
        checkAllParams(order, filterEntity.getTypes(), filterEntity.getGenres());
        return mangas;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "MangaService::findMangaById", key = "#id")
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
