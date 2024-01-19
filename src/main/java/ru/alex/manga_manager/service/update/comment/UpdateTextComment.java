package ru.alex.manga_manager.service.update.comment;

import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.dto.comment.UpdateCommentDto;

import java.util.Optional;

public class UpdateTextComment implements CommentUpdateComponent {
    @Override
    public void execute(UpdateCommentDto component, Comment updateComponent) {
        if (Optional.ofNullable(component.getValue()).isPresent()) {
            updateComponent.setText(component.getValue());
        }
    }
}
