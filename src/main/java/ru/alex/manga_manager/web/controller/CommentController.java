package ru.alex.manga_manager.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.alex.manga_manager.model.data.Comment;
import ru.alex.manga_manager.model.dto.CommentDto;
import ru.alex.manga_manager.model.dto.RegistrationNewCommentDto;
import ru.alex.manga_manager.repository.CommentRepository;
import ru.alex.manga_manager.util.converter.CommentConverter;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/{id}/comment")
public class CommentController {

    @Qualifier("defaultCommentConverter")
    private final CommentConverter<CommentDto, Comment> commentConverter;

    private final CommentRepository commentRepository;

    @GetMapping("/")
    public List<CommentDto> findCommentByMangaId(@PathVariable String id) {
        List<Comment> commentDtos = commentRepository.findAllByManga_IdAndParent_IdIsNull(id);
        return commentDtos.stream().map(commentConverter::convert).toList();
    }

    @PostMapping("/write")
    public List<CommentDto> writeComment(@PathVariable("id") String id,
                                         Authentication authentication,
                                         @RequestBody RegistrationNewCommentDto commentDto) {
        return null;
    }
}
