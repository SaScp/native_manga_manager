package ru.alex.manga_manager.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContactDto {

    @NotNull
    private String numberPhone;

    @NotNull
    private String description;
}
