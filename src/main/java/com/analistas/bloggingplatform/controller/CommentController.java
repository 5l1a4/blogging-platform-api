package com.analistas.bloggingplatform.controller;

import com.analistas.bloggingplatform.domain.dto.CommentRequest;
import com.analistas.bloggingplatform.domain.dto.CommentResponse;
import com.analistas.bloggingplatform.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getCommentsByPostId (@PathVariable Long postId) {
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getCommentById (@PathVariable Long postId, @PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentById(postId, id));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> saveComments (@PathVariable Long postId, @Valid @RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(commentService.createComment(postId, commentRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComments (@PathVariable Long postId, @PathVariable Long id, @Valid @RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(commentService.updateComment(postId, id,commentRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComments (@PathVariable Long postId, @PathVariable Long id) {
        commentService.deleteCommentById(postId, id);
        return ResponseEntity.noContent().build();
    }

}
