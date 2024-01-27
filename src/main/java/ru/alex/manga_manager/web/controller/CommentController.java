package ru.alex.manga_manager.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.alex.manga_manager.model.data.comment.Comment;
import ru.alex.manga_manager.model.dto.comment.CommentDto;
import ru.alex.manga_manager.model.dto.comment.RegistrationNewCommentDto;
import ru.alex.manga_manager.model.dto.comment.UpdateCommentDto;
import ru.alex.manga_manager.service.CommentService;
import ru.alex.manga_manager.util.converter.CommentConverter;
import ru.alex.manga_manager.util.exception.CommentAddException;
import ru.alex.manga_manager.util.exception.ForbiddenException;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/{id}/comment")
public class CommentController {

    @Qualifier("defaultCommentConverter")
    private final CommentConverter<CommentDto, Comment> commentConverter;

    @Qualifier("defaultCommentService")
    private final CommentService commentService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public List<CommentDto> findCommentByMangaId(@PathVariable String id) {
        List<CommentDto> commentDtos = commentService.findAllByMangaId(id).stream().map(commentConverter::convert).toList();
        return commentDtos;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add")
    public RedirectView writeComment(@PathVariable("id") String id,
                                     Authentication authentication,
                                     @RequestBody RegistrationNewCommentDto commentDto) {
        commentDto.setMangaId(id);
        commentDto.setAuthentication(authentication);
        if (commentService.add(commentDto)) {
            return new RedirectView("/v1/" + id + "/comment/");
        } else {
            throw new CommentAddException("add comment error");
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{comment-id}")
    public RedirectView deleteComment(@PathVariable("comment-id") String commentId,
                                      Authentication authentication,
                                      @PathVariable("id") String id) {
        if (Optional.ofNullable(authentication).isPresent() &&
                commentService.deleteComment(commentId, authentication)) {
            return new RedirectView("/v1/" + id + "/comment/");
        } else {
            throw new ForbiddenException("Forbidden");
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/update/{comment-id}")
    public RedirectView updateComment(@PathVariable("comment-id") String commentId,
                                      Authentication authentication,
                                      @RequestBody UpdateCommentDto updateCommentDto,
                                      @PathVariable("id") String id
    ) {

        if (Optional.ofNullable(authentication).isPresent() &&
                commentService.update(commentId, updateCommentDto, authentication)) {
            return new RedirectView("/v1/" + id + "/comment/");
        } else {
            throw new ResolutionException("resource not found");
        }
    }
}
