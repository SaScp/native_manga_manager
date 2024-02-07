package ru.alex.manga_manager.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder
@Data
public class ErrorResponse {

    public ZonedDateTime timestamp;

    public String code;


    public String message;
}
