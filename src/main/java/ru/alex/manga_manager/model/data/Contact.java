package ru.alex.manga_manager.model.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "t_contact")
public class Contact {
    @Id
    private String id;

    @Column(name = "number_phone", nullable = false)
    private String numberPhone;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "create_at", nullable = false)
    private Date createAt;
}
