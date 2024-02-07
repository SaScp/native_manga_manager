package ru.alex.manga_manager.service.update.comment;

import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.dto.comment.CommentDto;
import ru.alex.manga_manager.service.update.UpdateComponentStrategy;

import java.util.Optional;

public class UpdateTextComment implements UpdateComponentStrategy<CommentDto, Comment> {
    @Override
    public void execute(CommentDto component, Comment updateComponent) {
        if (Optional.ofNullable(component.getText()).isPresent()) {
            updateComponent.setText(component.getText());
        }
    }
}
