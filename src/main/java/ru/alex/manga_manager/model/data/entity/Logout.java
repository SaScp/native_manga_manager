package ru.alex.manga_manager.model.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("logout")
@AllArgsConstructor
public class Logout {

    private String id;

    private Date keepUntil;


}
