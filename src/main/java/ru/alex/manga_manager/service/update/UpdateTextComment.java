package ru.alex.manga_manager.service.update;

import ru.alex.manga_manager.model.data.Comment;

public class UpdateTextComment implements CommentUpdateComponent {
    @Override
    public void execute(String component, Comment updateComponent) {
        if (component != null) {
            updateComponent.setText(component);
        }
    }
}
