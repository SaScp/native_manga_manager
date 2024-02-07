package ru.alex.manga_manager.util.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.dto.comment.CommentDto;

@Component
public class CommentConverter implements Converter<Comment, CommentDto> {
    private final ModelMapper modelMapper;

    public CommentConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public Comment convertTo(CommentDto convertEntity) {
        return modelMapper.map(convertEntity, Comment.class);
    }

    @Override
    public CommentDto convertFrom(Comment convertEntity) {
        return modelMapper.map(convertEntity, CommentDto.class);
    }
}
