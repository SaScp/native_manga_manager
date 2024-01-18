package ru.alex.manga_manager.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.alex.manga_manager.model.data.Comment;
import ru.alex.manga_manager.model.dto.CommentDto;
import ru.alex.manga_manager.model.dto.RegistrationNewCommentDto;
import ru.alex.manga_manager.model.dto.UpdateCommentDto;
import ru.alex.manga_manager.service.CommentService;
import ru.alex.manga_manager.util.converter.CommentConverter;
import ru.alex.manga_manager.util.exception.CommentAddException;
import ru.alex.manga_manager.util.exception.ForbiddenException;

import java.util.List;
import java.util.Objects;
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

    @DeleteMapping("/delete/{comment-id}")
    public RedirectView deleteComment(@PathVariable("comment-id") String commentId, Authentication authentication, @PathVariable String id) {
        if (commentService.deleteComment(commentId, authentication)) {
            return new RedirectView("/v1/" + id + "/comment/");
        } else {
            throw new ForbiddenException("Forbidden");
        }
    }

    @PatchMapping("/updateComment/{comment-id}")
    public HttpStatus updateComment(@PathVariable("comment-id") String id, Authentication authentication, @RequestBody UpdateCommentDto updateCommentDto) {
        return commentService.update(id, updateCommentDto.getValue(), authentication)? HttpStatus.OK : HttpStatus.FAILED_DEPENDENCY;
    }
}
