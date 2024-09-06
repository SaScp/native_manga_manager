package ru.alex.manga_manager.model.data.contact;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;


@Data
@Document(collation = "contact")
public class Contact  implements Serializable {

    @NotNull
    private String id;

    @NotNull
    private String numberPhone;

    @NotNull
    private String description;

    @NotNull
    private Date createAt;
}
