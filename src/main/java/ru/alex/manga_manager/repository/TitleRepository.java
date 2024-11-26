package ru.alex.manga_manager.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.manga_manager.model.data.manga.Chapter;

import java.util.Set;
import java.util.UUID;

@Repository
public interface TitleRepository extends JpaRepository<Chapter, UUID> {

    Set<Chapter> findAllByManga_Id(@Param("manga_id") String id);
}
