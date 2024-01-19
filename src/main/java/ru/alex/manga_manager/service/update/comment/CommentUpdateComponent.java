package ru.alex.manga_manager.service.update.comment;

import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.dto.comment.UpdateCommentDto;
import ru.alex.manga_manager.service.update.UpdateComponent;

public interface CommentUpdateComponent extends UpdateComponent<UpdateCommentDto, Comment> {
}
