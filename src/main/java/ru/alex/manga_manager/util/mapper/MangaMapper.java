package ru.alex.manga_manager.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.dto.manga.MangaDto;

import java.util.List;

@Mapper
public interface MangaMapper {

    MangaMapper INSTANCE = Mappers.getMapper(MangaMapper.class);

    MangaDto mangaToMangaDto(Manga manga);

    Manga mangaDtoToManga(MangaDto mangaDto);

    List<MangaDto> mangasToMangaDtos(List<Manga> manga);

    List<Manga> mangaDtosToMangas(List<MangaDto> mangaDto);
}
