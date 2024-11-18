package ru.alex.manga_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex.manga_manager.model.data.manga.Chapter;
import ru.alex.manga_manager.model.data.manga.Manga;
import ru.alex.manga_manager.model.dto.manga.ChapterDto;
import ru.alex.manga_manager.repository.MangaRepository;
import ru.alex.manga_manager.repository.TitleRepository;
import ru.alex.manga_manager.service.MangaService;
import ru.alex.manga_manager.service.TitleService;
import ru.alex.manga_manager.util.exception.MangaNotFoundException;
import ru.alex.manga_manager.util.mapper.TitleMapper;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class DefaultTitleService implements TitleService {

    private final TitleRepository titleRepository;


    private final MangaRepository mangaRepository;



    @Override
    public Set<ChapterDto> findAllByManga(String mangaId) {
        return TitleMapper.INSTANCE.setChapterToSetChapterDto(titleRepository.findAllByMangaId(mangaId)
                .orElseThrow(() -> new MangaNotFoundException("title by id not found")));
    }

    @Override
    public boolean addSetChapter(Set<ChapterDto> chapterDtos, String mangaId) {
        try{
            Manga manga = mangaRepository.findById(mangaId)
                    .orElseThrow(() -> new MangaNotFoundException("Manga with id: " + mangaId + " Not Found"));
            Set<Chapter> chapters = TitleMapper.INSTANCE.setChapterDtoToSetChapter(chapterDtos);
            chapters.stream().map(e -> {
                manga.addChapter(e);
                return e;
            });
            mangaRepository.save(manga);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
