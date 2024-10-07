package ru.alex.manga_manager.service.factory;

import org.springframework.stereotype.Component;
import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.dto.comment.CommentDto;
import ru.alex.manga_manager.model.dto.comment.RegistrationNewCommentDto;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class CommentCreateFactory implements CreateFactory<Comment, RegistrationNewCommentDto> {
    @Override
    public Comment create(RegistrationNewCommentDto entity) {
        return Comment.builder()
                .id(UUID.randomUUID().toString())
                .text(entity.getText())
                .comments(new ArrayList<>())
                .createAt(ZonedDateTime.now())
                .updateAt(ZonedDateTime.now())
                .build();
    }
}
