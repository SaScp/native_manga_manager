package ru.alex.manga_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.manga_manager.model.data.comment.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {


    List<Comment> findAllByManga_IdAndParent_IdIsNull(String manga_id);

}
