package ru.alex.manga_manager.service;

import org.springframework.security.core.Authentication;
import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.dto.comment.CommentDto;
import ru.alex.manga_manager.model.dto.comment.RegistrationNewCommentDto;

import java.util.List;

public interface CommentService {

    boolean add(RegistrationNewCommentDto commentDto);

    List<Comment> findAllByMangaId(String id);

    boolean deleteComment(String id, Authentication authentication);

    boolean update(String id, CommentDto updateCommentEntity, Authentication authentication);
}
