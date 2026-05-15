package com.analistas.bloggingplatform.domain.mapper;

import com.analistas.bloggingplatform.domain.dto.CommentRequest;
import com.analistas.bloggingplatform.domain.dto.CommentResponse;
import com.analistas.bloggingplatform.domain.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity (CommentRequest commentRequest) {
        return new Comment(
                commentRequest.getText(),
                commentRequest.getAuthor()
        );
    }

    public CommentResponse toResponse(Comment comment){
        return new CommentResponse(
                comment.getId(),
                comment.getText(),
                comment.getAuthor(),
                comment.getCreatedAt(),
                comment.getPost().getId()
        );
    }
}
