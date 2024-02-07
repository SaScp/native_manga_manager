package ru.alex.manga_manager.model.dto.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.Authentication;

@Data
public class RegistrationNewCommentDto {

    private String mangaId;

    @JsonIgnore
    private Authentication authentication;

    private String text;

    private String parentId;


}
