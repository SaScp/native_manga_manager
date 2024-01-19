package ru.alex.manga_manager.service;

import org.springframework.security.core.Authentication;
import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.dto.comment.RegistrationNewCommentDto;
import ru.alex.manga_manager.model.dto.comment.UpdateCommentDto;

import java.util.List;

public interface CommentService {

    boolean add(RegistrationNewCommentDto commentDto);

    List<Comment> findAllByMangaId(String id);

    boolean deleteComment(String id, Authentication authentication);

    boolean update(String id, UpdateCommentDto updateCommentDto, Authentication authentication);
}
