package ru.alex.manga_manager.model.dto.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.Authentication;

import java.util.Optional;

@Data
public class UpdateCommentDto {
    private String value;
}
