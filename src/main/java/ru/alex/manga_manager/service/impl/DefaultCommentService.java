package ru.alex.manga_manager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alex.manga_manager.model.data.Comment;
import ru.alex.manga_manager.model.data.Manga;
import ru.alex.manga_manager.model.data.User;
import ru.alex.manga_manager.model.dto.RegistrationNewCommentDto;
import ru.alex.manga_manager.repository.CommentRepository;
import ru.alex.manga_manager.repository.MangaRepository;
import ru.alex.manga_manager.repository.UserRepository;
import ru.alex.manga_manager.service.CommentService;
import ru.alex.manga_manager.util.exception.CommentNotFoundException;
import ru.alex.manga_manager.util.exception.MangaNotFoundException;
import ru.alex.manga_manager.util.exception.UserNotFoundException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultCommentService implements CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final MangaRepository mangaRepository;

    @Override
    public boolean add(RegistrationNewCommentDto commentDto) {
        Comment comment = new Comment();

        String userEmail = commentDto.getAuthentication().getName();
        User user = userRepository.findByEmail(userEmail).orElseThrow(() ->
                new UserNotFoundException("User: " + userEmail + " Not Found"));
        Manga manga = mangaRepository.findById(commentDto.getMangaId()).orElseThrow(() ->
                new MangaNotFoundException("Manga with id " + commentDto.getMangaId() + " Not Found"));
        Comment parentComment = commentDto.getParentId() == null? null : commentRepository.findById(commentDto.getParentId()).orElseThrow(CommentNotFoundException::new);

        comment.setId(UUID.randomUUID().toString());
        comment.setCreateAt(ZonedDateTime.now());
        comment.setUpdateAt(ZonedDateTime.now());
        comment.setComments(new ArrayList<>());
        comment.setText(commentDto.getText());
        comment.setParent(parentComment);
        comment.setAuthor(user);
        comment.setManga(manga);

        manga.addComment(comment);
        user.addComment(comment);
        try {
            commentRepository.save(comment);
            userRepository.save(user);
            mangaRepository.save(manga);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Comment> findAllByMangaId(String id) {
        return commentRepository.findAllByManga_IdAndParent_IdIsNull(id);
    }
}
