package ru.alex.manga_manager.util.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.dto.comment.CommentDto;

@Component
@RequiredArgsConstructor
public class DefaultCommentConverter implements CommentConverter<CommentDto, Comment> {

    private final ModelMapper modelMapper;
    @Override
    public CommentDto convert(Comment convertDto) {
        return modelMapper.map(convertDto, CommentDto.class);
    }
}
