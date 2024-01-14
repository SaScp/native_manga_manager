package ru.alex.manga_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.manga_manager.model.data.Genre;


/**Repository for {@link ru.alex.manga_manager.model.data.Genre}*/
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>  {
}
