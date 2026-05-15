package com.analistas.bloggingplatform.service;

import com.analistas.bloggingplatform.domain.dto.CommentRequest;
import com.analistas.bloggingplatform.domain.dto.CommentResponse;

import java.util.List;

public interface CommentService {

    List<CommentResponse> getCommentsByPostId(Long id);

    CommentResponse getCommentById(Long postId, Long id);

    CommentResponse createComment(Long postId, CommentRequest commentRequest);

    CommentResponse updateComment(Long postId, Long id, CommentRequest commentRequest);

    void deleteCommentById(Long postId, Long id);

}
