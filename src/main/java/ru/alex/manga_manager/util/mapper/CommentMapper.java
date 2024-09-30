package ru.alex.manga_manager.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.data.user.User;
import ru.alex.manga_manager.model.dto.comment.CommentDto;
import ru.alex.manga_manager.model.dto.user.UserDto;

import java.util.List;

@Mapper
public interface CommentMapper {


    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDto commentToCommentDto(Comment comment);

    Comment commentDtoToComment(CommentDto commentDto);

    List<CommentDto> commentsToCommentDtos(List<Comment> comments);

    List<Comment> commentDtosToComments(List<CommentDto> commentDtos);
}
