package ru.alex.manga_manager.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.alex.manga_manager.model.data.Manga;
import ru.alex.manga_manager.model.dto.MangaDto;
import ru.alex.manga_manager.repository.MangaRepository;
import ru.alex.manga_manager.service.MangaService;
import ru.alex.manga_manager.util.converter.DefaultContactConverter;
import ru.alex.manga_manager.util.converter.MangaConverter;

import java.util.ArrayList;
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
    public List<MangaDto> findAllMangas(@RequestParam(value = "type", required = false) ArrayList<Long> types,
                                        @RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNumber,
                                        @RequestParam(value = "genres", required = false) ArrayList<Long> genres,
                                        @RequestParam(value = "order", required = false) String order,
                                        @RequestParam(value = "count", required = false, defaultValue = "20") Integer pageSize
    ) {
        return mangaService.findAllMangas(pageNumber, genres, types, order, pageSize).stream().map(mangaConverter::convert).toList();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/search")
    public List<MangaDto> searchMangaAboutTitle(@RequestParam("query") String title,
                                                @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        return mangaService.search(title, page).stream().map(mangaConverter::convert).toList();
    }

    @GetMapping("/title/{id}")
    public MangaDto findMangaById(@PathVariable("id") String id) {
        return mangaConverter.convert(mangaService.findMangaById(id));
    }

}
