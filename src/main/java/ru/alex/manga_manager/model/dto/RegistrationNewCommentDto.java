package ru.alex.manga_manager.model.dto;

import lombok.Data;
import org.springframework.security.core.Authentication;

@Data
public class RegistrationNewCommentDto {

    private String mangaId;

    private Authentication authentication;

    private String text;

    private String parentId;


}
