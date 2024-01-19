package ru.alex.manga_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.manga_manager.model.data.manga.Genre;


/**Repository for {@link Genre}*/
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>  {
}
