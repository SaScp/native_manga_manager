package ru.alex.manga_manager.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alex.manga_manager.model.data.manga.Manga;

import java.util.Collection;
import java.util.List;


/**
 * Repository for {@link Manga}
 */
@Repository
public interface MangaRepository extends JpaRepository<Manga, String> {



    @Query("select manga from Manga manga join manga.genres genres where genres.id in :genre and manga.type.id in :types")
    List<Manga> findAllByTypeInAndGenresIn(@Param("types") Collection<Long> types, @Param("genre") Collection<Long> genres, Pageable pageable);


    @Query(value = "select manga from Manga manga join manga.genres genres where genres.id in :genre")
    List<Manga> findAllByGenresIn(@Param("genre") Collection<Long> genres, Pageable pageable);

    @Query(value = "select manga from Manga manga where manga.type.id in :types")
    List<Manga> findAllByTypesIn(@Param("types") Collection<Long> types, PageRequest pageRequest);


    @Query("select manga from Manga manga inner join manga.comments comment where comment.parent = NULL")
    List<Manga> findRootComments();

    @Query(value = "select manga from Manga manga where upper(manga.mainName) like concat('%', upper(:title), '%') or upper(manga.secondaryName) like concat('%', upper(:title), '%')")
    List<Manga> findByMainNameStartingWithOrSecondaryNameStartingWith(@Param("title") String title, Pageable pageable);

}
