package ru.alex.manga_manager.util.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.manga_manager.model.data.manga.Chapter;
import ru.alex.manga_manager.model.dto.manga.ChapterDto;

import java.util.Set;

@Mapper
public interface TitleMapper {

    TitleMapper INSTANCE = Mappers.getMapper(TitleMapper.class);

    Set<ChapterDto> setChapterToSetChapterDto(Set<Chapter> chapter);

    Set<Chapter> setChapterDtoToSetChapter(Set<ChapterDto> chapterDtos);
}
