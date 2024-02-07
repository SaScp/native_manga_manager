package ru.alex.manga_manager.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.alex.manga_manager.model.data.entity.FilterEntity;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.data.entity.SearchEntity;
import ru.alex.manga_manager.model.dto.manga.MangaDto;
import ru.alex.manga_manager.service.MangaService;
import ru.alex.manga_manager.util.annotation.FilterParam;
import ru.alex.manga_manager.util.annotation.SearchParam;
import ru.alex.manga_manager.util.converter.MangaConverter;
import ru.alex.manga_manager.util.converter.manga.MangaConverter;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/manga")
public class MangaController {

    @Qualifier("defaultMangaService")
    private final MangaService mangaService;

    private final MangaConverter mangaConverter;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/titles")
    public List<MangaDto> findAllMangas(@FilterParam FilterEntity filterEntity) {
        return mangaService.findAll(filterEntity)
                .stream().map(mangaConverter::convertFrom).toList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/search")
    public List<MangaDto> searchMangaAboutTitle(@SearchParam SearchEntity search) {
        return mangaService.search(search).stream().map(mangaConverter::convertFrom).toList();
    }



}
