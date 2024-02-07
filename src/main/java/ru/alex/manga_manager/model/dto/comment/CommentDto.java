package ru.alex.manga_manager.model.dto.comment;

import lombok.Data;
import ru.alex.manga_manager.model.data.comment.Comment;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * DTO for {@link Comment}
 */
@Data
public class CommentDto implements Serializable {

    private String id;
    private String text;
    private ZonedDateTime createAt;
    private ZonedDateTime updateAt;
    private List<CommentDto> comments;
}