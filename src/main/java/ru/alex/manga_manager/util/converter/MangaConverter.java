package ru.alex.manga_manager.util.converter;

import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.dto.manga.MangaDto;

@Component
public class MangaConverter implements ConverterStrategy<Manga, MangaDto>{
    @Override
    public Manga convertTo(MangaDto convertEntity) {
        return null;
    }

    @Override
    public MangaDto convertFrom(Manga convertEntity) {
        return null;
    }
}
