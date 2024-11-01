package ru.alex.manga_manager.service;

import ru.alex.manga_manager.model.dto.manga.ChapterDto;

import java.util.Set;

public interface TitleService {

    Set<ChapterDto> findAllByManga(String mangaId);

    boolean addSetChapter(Set<ChapterDto> chapterDtos, String mangaId);

}
