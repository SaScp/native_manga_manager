package ru.alex.manga_manager.model.dto;

import lombok.Data;
import lombok.Value;
import ru.alex.manga_manager.model.data.Comment;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * DTO for {@link Comment}
 */
@Data
public class CommentDto implements Serializable {
    String id;
    String text;
    ZonedDateTime createAt;
    ZonedDateTime updateAt;
    List<CommentDto> comments;
}