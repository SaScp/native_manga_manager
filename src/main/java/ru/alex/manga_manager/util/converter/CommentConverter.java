package ru.alex.manga_manager.util.converter;

import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.dto.comment.CommentDto;

@Component
public class CommentConverter implements ConverterStrategy<Comment, CommentDto>{
    @Override
    public Comment convertTo(CommentDto convertEntity) {
        return null;
    }

    @Override
    public CommentDto convertFrom(Comment convertEntity) {
        return null;
    }
}
