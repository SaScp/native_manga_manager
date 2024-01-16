package ru.alex.manga_manager.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.alex.manga_manager.model.data.Comment;
import ru.alex.manga_manager.model.dto.CommentDto;
import ru.alex.manga_manager.model.dto.RegistrationNewCommentDto;
import ru.alex.manga_manager.service.CommentService;
import ru.alex.manga_manager.util.converter.CommentConverter;

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

    @PostMapping("/write")
    public HttpStatus writeComment(@PathVariable("id") String id,
                                   Authentication authentication,
                                   @RequestBody RegistrationNewCommentDto commentDto) {
        commentDto.setMangaId(id);
        commentDto.setAuthentication(authentication);
        return  commentService.add(commentDto)? HttpStatus.OK : HttpStatus.FAILED_DEPENDENCY;
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteComment(@PathVariable("id") String id, Authentication authentication) {
        commentService.deleteComment(id, authentication);
        return HttpStatus.OK;
    }

    @PatchMapping("/updateComment")
    public HttpStatus updateComment(@PathVariable String id, Authentication authentication, @RequestBody Optional<String> text) {
        return commentService.update(id, Objects.requireNonNull(text.get()), authentication)? HttpStatus.OK : HttpStatus.FAILED_DEPENDENCY;
    }
}
