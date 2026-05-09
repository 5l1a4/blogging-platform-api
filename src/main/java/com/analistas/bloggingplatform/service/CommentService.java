package com.analistas.bloggingplatform.service;

import com.analistas.bloggingplatform.domain.dto.CommentRequest;
import com.analistas.bloggingplatform.domain.dto.CommentResponse;

import java.util.List;

public interface CommentService {

    List<CommentResponse> getCommentsByPostId(Long id);

    CommentResponse getCommentById(Long id);

    CommentResponse createComment(CommentRequest commentRequest, Long postId);

    CommentResponse updateComment(Long id, CommentRequest commentRequest);

    void deleteCommentById(Long id);

}
