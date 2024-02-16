package ru.alex.manga_manager.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder
@Data
public class ErrorResponse {

    private ZonedDateTime timestamp;

    private String code;

    private String path;

    private String message;
}
