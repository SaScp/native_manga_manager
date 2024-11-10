package ru.alex.manga_manager.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.NodeList;
import ru.alex.manga_manager.model.data.entity.FilterEntity;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.data.entity.SearchEntity;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.manga.MangaDto;
import ru.alex.manga_manager.model.dto.user.UserDto;
import ru.alex.manga_manager.repository.MangaRepository;
import ru.alex.manga_manager.service.MangaService;


import ru.alex.manga_manager.service.filterhandler.*;
import ru.alex.manga_manager.util.exception.MangaNotFoundException;
import ru.alex.manga_manager.util.mapper.MangaMapper;


import java.util.*;

@Service
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

    @CachePut(value = "save", key = "#mangaDto", unless = "#result == null")
    @Override
    public Manga save(MangaDto mangaDto) {
        Manga manga = MangaMapper.INSTANCE.mangaDtoToManga(mangaDto);
        mangaDto.setId(UUID.randomUUID().toString());
        mangaRepository.save(manga);
        return manga;
    }

    @Cacheable(value = "search", key = "#search.title")
    @Override
    public List<Manga> search(SearchEntity search) {
        PageRequest pageRequest = PageRequest.of(search.getPage(), 20);
        return mangaRepository.findByMainNameStartingWithOrSecondaryNameStartingWith(search.getTitle(), pageRequest);
    }

    @Override
    public List<Manga> findAll(FilterEntity filterEntity) {
        return startHandler.handleRequest(filterEntity);
    }

    @Override
    @Cacheable(value = "findMangaById", unless = "#result == null", key = "#id")
    public Manga findMangaById(String id) {
        return this.mangaRepository.findById(id)
                .orElseThrow(() -> new MangaNotFoundException("Manga with id: " + id + " Not Found"));
    }

    @Override
    public List<Manga> findAllByUserId(String id) {
        return mangaRepository.findAllByUsersIs(id);
    }
}
