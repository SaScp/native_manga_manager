package ru.alex.manga_manager.util.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.dto.manga.MangaDto;
import ru.alex.manga_manager.util.exception.MangaConverterException;


@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultMangaDtoConverter implements MangaConverter<MangaDto, Manga> {

    private final ModelMapper modelMapper;
    @Override
    public MangaDto convert(Manga convertDto) {
        if (convertDto == null) {
            log.error("convertDto is null",new MangaConverterException());
            return null;
        }
        return modelMapper.map(convertDto, MangaDto.class);
    }
}
