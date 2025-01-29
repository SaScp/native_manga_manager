package ru.alex.manga_manager.service.impl;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.manga_manager.model.data.entity.FilterEntity;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.data.entity.SearchEntity;
import ru.alex.manga_manager.model.dto.manga.MangaDto;
import ru.alex.manga_manager.repository.MangaRepository;
import ru.alex.manga_manager.service.MangaService;


import ru.alex.manga_manager.service.filterhandler.*;
import ru.alex.manga_manager.util.exception.MangaNotFoundException;
import ru.alex.manga_manager.util.mapper.MangaMapper;


import java.util.*;

@Service
@Order
@Transactional(readOnly = true)
public class DefaultMangaService implements MangaService {

    private final MangaRepository mangaRepository;

    StartHandler startHandler;

    public DefaultMangaService(MangaRepository mangaRepository) {
        this.mangaRepository = mangaRepository;

        startHandler = new StartHandler(mangaRepository);
        Handler allParametersHandler = new AllParametersHandler(mangaRepository);
        Handler genresOrTypeOrderHandler = new GenresOrTypeOrderHandler(mangaRepository);
        Handler orderIsEmptyHandler = new OrderIsEmptyHandler(mangaRepository);
        Handler typesAndGenresIdsHandler = new TypesAndGenresIdsHandler(mangaRepository);

        startHandler.setNextHandler(allParametersHandler);
        allParametersHandler.setNextHandler(genresOrTypeOrderHandler);
        genresOrTypeOrderHandler.setNextHandler(orderIsEmptyHandler);
        orderIsEmptyHandler.setNextHandler(typesAndGenresIdsHandler);
    }

    @CachePut(value = "manga", key = "#mangaDto.id", unless = "#result == null")
    @Override
    public Manga save(MangaDto mangaDto) {
        Manga manga = MangaMapper.INSTANCE.mangaDtoToManga(mangaDto);
        mangaDto.setId(UUID.randomUUID().toString());
        mangaRepository.save(manga);
        return manga;
    }

    @Cacheable(value = "search", key = "#search.title")
    @Override
    public List<MangaDto> search(SearchEntity search) {
        PageRequest pageRequest = PageRequest.of(search.getPage(), 20);
        return MangaMapper.INSTANCE.mangasToMangaDtos(mangaRepository.findByMainNameStartingWithOrSecondaryNameStartingWith(search.getTitle(), pageRequest));
    }

    @Override
    @Cacheable(value = "catalog", key = "#filterEntity")
    public List<MangaDto> findAll(FilterEntity filterEntity) {
        return MangaMapper.INSTANCE.mangasToMangaDtos(startHandler.handleRequest(filterEntity));
    }

    @Override
    @Cacheable(value = "manga", unless = "#result == null", key = "#id")
    public MangaDto findMangaById(String id) {
        return MangaMapper.INSTANCE.mangaToMangaDto(this.mangaRepository.findById(id)
                .orElseThrow(() -> new MangaNotFoundException("Manga with id: " + id + " Not Found")));
    }

    @Override
    public List<Manga> findAllByUserId(String id) {
        return mangaRepository.findAllByUsersIs(id);
    }
}
