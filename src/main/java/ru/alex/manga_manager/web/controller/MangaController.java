package ru.alex.manga_manager.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.alex.manga_manager.model.data.FilterEntity;
import ru.alex.manga_manager.model.data.Manga;
import ru.alex.manga_manager.model.data.SearchEntity;
import ru.alex.manga_manager.model.dto.MangaDto;
import ru.alex.manga_manager.service.MangaService;
import ru.alex.manga_manager.util.annotation.FilterParam;
import ru.alex.manga_manager.util.annotation.SearchParam;
import ru.alex.manga_manager.util.converter.MangaConverter;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/manga")
public class MangaController {

    @Qualifier("defaultMangaService")
    private final MangaService mangaService;

    @Qualifier("defaultMangaDtoConverter")
    private final MangaConverter<MangaDto, Manga> mangaConverter;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/catalog")
    public List<MangaDto> findAllMangas(@FilterParam FilterEntity filterEntity) {
        return mangaService.findAllMangas(filterEntity)
                .stream().map(mangaConverter::convert).toList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/search")
    public List<MangaDto> searchMangaAboutTitle(@SearchParam SearchEntity search) {
        return mangaService.search(search).stream().map(mangaConverter::convert).toList();
    }



}
