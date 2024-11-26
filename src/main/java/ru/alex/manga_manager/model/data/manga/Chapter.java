package ru.alex.manga_manager.model.data.manga;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
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

    @ElementCollection
    @CollectionTable(name = "t_page", joinColumns = @JoinColumn(name = "chapter_id", referencedColumnName = "chapter_id"))
    @Column(name = "url")
    private Set<String> urls;


}
