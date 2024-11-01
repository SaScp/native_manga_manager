package ru.alex.manga_manager.model.data.manga;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Data
@Entity
@Table(name = "t_chapter")
public class Chapter {

    @Id
    @Column(name = "chapter_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manga_id", referencedColumnName = "id")
    private Manga manga;


    @Column(name = "chapter_url", nullable = false)
    private String url;

}
