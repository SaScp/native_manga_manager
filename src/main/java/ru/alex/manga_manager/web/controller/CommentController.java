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
        return commentService.findAllByMangaId(id).stream().map(commentConverter::convert).toList();
    }

    @ResponseStatus(HttpStatus.PERMANENT_REDIRECT)
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

    @ResponseStatus(HttpStatus.PERMANENT_REDIRECT)
    @DeleteMapping("/delete/{comment-id}")
    public RedirectView deleteComment(@PathVariable("comment-id") String commentId,
                                      Optional<Authentication> authentication,
                                      @PathVariable String id) {
        if (commentService.deleteComment(commentId, authentication.orElseThrow(() ->
                new ForbiddenException("Forbidden")))) {
            return new RedirectView("/v1/" + id + "/comment/");
        } else {
            throw new ForbiddenException("Forbidden");
        }
    }

    @ResponseStatus(HttpStatus.PERMANENT_REDIRECT)
    @PatchMapping("/update/{comment-id}")
    public RedirectView updateComment(@PathVariable("comment-id") String commentId,
                                      Optional<Authentication> authentication,
                                      @RequestBody UpdateCommentDto updateCommentDto,
                                      @PathVariable String id) {
        if (authentication.isPresent() &&
                commentService.update(commentId, updateCommentDto, authentication.get())) {
            return new RedirectView("/v1/" + id + "/comment/");
        } else {
            throw new ResolutionException("resource not found");
        }
    }
}
