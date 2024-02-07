package ru.alex.manga_manager.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/manga")
@Tag(name = "MangaController", description = "Контроллер для взаимодействия с данными Манг")
public class MangaController {

    @Qualifier("defaultMangaService")
    private final MangaService mangaService;

    private final MangaConverter mangaConverter;

    @Operation(
            summary = "Просмотр всех манг",
            description = "позволяет просматривать каталог всех доступных манг в API"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/titles")
    public List<MangaDto> findAllMangas(@FilterParam @Parameter(description = "Параметры для фильтрации") FilterEntity filterEntity) {
        return mangaService.findAll(filterEntity)
                .stream().map(mangaConverter::convertFrom).toList();
    }

    @Operation(
            summary = "Поиск манги",
            description = "позволяет искать мангу доступную в API"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/search")
    public List<MangaDto> searchMangaAboutTitle(@SearchParam @Parameter(description = "Параметры для поиска") SearchEntity search) {
        return mangaService.search(search).stream().map(mangaConverter::convertFrom).toList();
    }



}
