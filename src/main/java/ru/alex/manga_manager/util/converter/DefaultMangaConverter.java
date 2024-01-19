package ru.alex.manga_manager.util.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.dto.manga.MangaDto;

@Component
@RequiredArgsConstructor
public class DefaultMangaConverter implements MangaConverter<Manga, MangaDto> {

    private final ModelMapper modelMapper;
    @Override
    public Manga convert(MangaDto convertDto) {
        if (convertDto != null) {
            return modelMapper.map(convertDto, Manga.class);
        }
       return null;
    }
}
