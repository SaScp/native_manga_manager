package ru.alex.manga_manager.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.dto.group.ViewMangaJsonGroup;
import ru.alex.manga_manager.model.dto.manga.ChapterDto;
import ru.alex.manga_manager.model.dto.manga.MangaDto;
import ru.alex.manga_manager.service.MangaService;

import ru.alex.manga_manager.service.TitleService;
import ru.alex.manga_manager.util.mapper.MangaMapper;


import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manga/{id}")
@Tag(name = "TitleController", description = "Контроллер для взаимодействия с данными глав")
public class TitleController {

    @Qualifier("defaultMangaService")
    private final MangaService mangaService;

    private final TitleService titleService;

    @JsonView(value = ViewMangaJsonGroup.InnerDataTitle.class)
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public MangaDto findMangaById(@PathVariable("id") String id) {
        return mangaService.findMangaById(id);
    }


    @GetMapping(value = "/chapters", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<ChapterDto> findChaptersByMangaId(@PathVariable("id") String id) {
        return titleService.findAllByManga(id);
    }

    @PostMapping("/add")
    public boolean addChapterInTitle(@PathVariable("id") String id, @RequestBody ChapterDto chapterDto) {
        return false;
    }
}
