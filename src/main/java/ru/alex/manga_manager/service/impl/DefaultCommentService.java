package ru.alex.manga_manager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.manga_manager.model.data.Comment;
import ru.alex.manga_manager.model.data.Manga;
import ru.alex.manga_manager.model.data.User;
import ru.alex.manga_manager.model.dto.RegistrationNewCommentDto;
import ru.alex.manga_manager.repository.CommentRepository;
import ru.alex.manga_manager.repository.MangaRepository;
import ru.alex.manga_manager.repository.UserRepository;
import ru.alex.manga_manager.service.CommentService;
import ru.alex.manga_manager.service.update.CommentUpdateComponent;
import ru.alex.manga_manager.service.update.UpdateTextComment;
import ru.alex.manga_manager.util.exception.CommentNotFoundException;
import ru.alex.manga_manager.util.exception.ForbiddenException;
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
    @Transactional
    public boolean add(RegistrationNewCommentDto commentDto) {
        Comment comment = new Comment();
        if (commentDto.getAuthentication() == null) {
            throw new ForbiddenException("Forbidden");
        }

        String id = commentDto.getAuthentication().getName();
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User: " + id + " Not Found"));
        Manga manga = mangaRepository.findById(commentDto.getMangaId()).orElseThrow(() ->
                new MangaNotFoundException("Manga with id " + commentDto.getMangaId() + " Not Found"));
        Comment parentComment = commentDto.getParentId() == null ? null : findById(commentDto.getParentId());

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

    @Transactional
    public boolean update(String id, String newText, Authentication authentication) {
        try {
            Comment comment = findById(id);
            if (authentication.getName().equals(comment.getAuthor().getId())) {
                new UpdateTextComment().execute(newText, comment);
                commentRepository.save(comment);
            } else {
                throw new ForbiddenException("Forbidden");
            }
            return true;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            return false;
        }

    }
    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAllByMangaId(String id) {
        return commentRepository.findAllByManga_IdAndParent_IdIsNull(id);
    }

    @Transactional(readOnly = true)
    public Comment findById(String id) {
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

    @Transactional
    public void deleteComment(String id, Authentication authentication) {
        if (!authentication.getName().equals(findById(id).getId())) {
            commentRepository.deleteById(id);
        } else {
            throw new ForbiddenException("Forbidden");
        }
    }
}
