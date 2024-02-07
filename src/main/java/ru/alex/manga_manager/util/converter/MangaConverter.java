package ru.alex.manga_manager.util.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.dto.contact.ContactDto;
import ru.alex.manga_manager.model.dto.manga.MangaDto;

@Component
public class MangaConverter implements Converter<Manga, MangaDto> {

    private final ModelMapper modelMapper;

    public MangaConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Manga convertTo(MangaDto convertEntity) {
        return modelMapper.map(convertEntity, Manga.class);
    }

    @Override
    public MangaDto convertFrom(Manga convertEntity) {
        return modelMapper.map(convertEntity, MangaDto.class);
    }
}
