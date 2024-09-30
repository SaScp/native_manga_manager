package ru.alex.manga_manager.service.factory;

import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.dto.comment.CommentDto;

@Component
public class CommentCreateFactory implements CreateFactory<Comment, CommentDto> {
    @Override
    public Comment create(CommentDto entity) {
        return null;
    }
}
