package ru.alex.manga_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.alex.manga_manager.model.data.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

    @Query("SELECT c FROM Comment c WHERE c.parent IS NULL")
    List<Comment> findRootComments();
}
