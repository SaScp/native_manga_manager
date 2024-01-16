package ru.alex.manga_manager.service;

import org.springframework.security.core.Authentication;
import ru.alex.manga_manager.model.data.Comment;
import ru.alex.manga_manager.model.dto.CommentDto;
import ru.alex.manga_manager.model.dto.RegistrationNewCommentDto;

import java.util.List;

public interface CommentService {

    boolean add(RegistrationNewCommentDto commentDto);

    List<Comment> findAllByMangaId(String id);

    void deleteComment(String id, Authentication authentication);

    boolean update(String id, String newText, Authentication authentication);
}
