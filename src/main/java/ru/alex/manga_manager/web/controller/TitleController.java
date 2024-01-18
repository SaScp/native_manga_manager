package ru.alex.manga_manager.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.manga_manager.model.data.Comment;
import ru.alex.manga_manager.model.data.Manga;
import ru.alex.manga_manager.model.dto.CommentDto;
import ru.alex.manga_manager.model.dto.MangaDto;
import ru.alex.manga_manager.repository.CommentRepository;
import ru.alex.manga_manager.service.MangaService;
import ru.alex.manga_manager.util.converter.CommentConverter;
import ru.alex.manga_manager.util.converter.MangaConverter;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/manga/{id}")
public class TitleController {

    @Qualifier("defaultMangaService")
    private final MangaService mangaService;

    @Qualifier("defaultMangaDtoConverter")
    private final MangaConverter<MangaDto, Manga> mangaConverter;

    @GetMapping("/")
    public MangaDto findMangaById(@PathVariable("id") String id) {
        return mangaConverter.convert(mangaService.findMangaById(id));
    }


    @GetMapping("/chapters")
    public List<String> findChaptersByMangaId(@PathVariable("id") String id) {
        //TODO in the progress.....
        return null;
    }
}
