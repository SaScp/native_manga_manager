package ru.alex.manga_manager.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.dto.manga.MangaDto;
import ru.alex.manga_manager.service.MangaService;
import ru.alex.manga_manager.util.converter.MangaConverter;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/manga/{id}")
@Tag(name = "TitleController", description = "Контроллер для взаимодействия с данными глав")
public class TitleController {

    @Qualifier("defaultMangaService")
    private final MangaService mangaService;


    private final MangaConverter mangaConverter;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public MangaDto findMangaById(@PathVariable("id") String id) {
        return mangaConverter.convertFrom(mangaService.findMangaById(id));
    }


    @GetMapping(value = "/chapters", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> findChaptersByMangaId(@PathVariable("id") String id) {
        //TODO in the progress.....
        return null;
    }
}
