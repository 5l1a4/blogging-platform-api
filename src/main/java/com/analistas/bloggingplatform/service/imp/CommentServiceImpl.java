package com.analistas.bloggingplatform.service.imp;

import com.analistas.bloggingplatform.domain.dto.CommentRequest;
import com.analistas.bloggingplatform.domain.dto.CommentResponse;
import com.analistas.bloggingplatform.domain.entity.Comment;
import com.analistas.bloggingplatform.domain.entity.Post;
import com.analistas.bloggingplatform.domain.mapper.CommentMapper;
import com.analistas.bloggingplatform.exception.EntityNotFound;
import com.analistas.bloggingplatform.repository.CommentRepository;
import com.analistas.bloggingplatform.repository.PostRepository;
import com.analistas.bloggingplatform.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepo;

    private final PostRepository postRepo;

    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepo,  PostRepository postRepo,  CommentMapper commentMapper) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentResponse> getCommentsByPostId(Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new EntityNotFound("Post not found"));
        return post.getComments().stream().map(commentMapper::toResponse).toList();
    }

    @Override
    public CommentResponse getCommentById(Long id) {
        return commentMapper.toResponse(commentRepo.findById(id).orElseThrow(() -> new EntityNotFound("Comment not found")));
    }

    @Override
    public CommentResponse createComment(CommentRequest commentRequest, Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new EntityNotFound("Post not found"));
        Comment commentCreate = commentMapper.toEntity(commentRequest);
        commentCreate.setPost(post);
        return commentMapper.toResponse(commentRepo.save(commentCreate));
    }

    @Override
    public CommentResponse updateComment(Long id, CommentRequest commentRequest) {
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new EntityNotFound("Comment not found"));
        comment.setAuthor(commentRequest.getAuthor());
        comment.setText(commentRequest.getText());
        return commentMapper.toResponse(commentRepo.save(comment));
    }

    @Override
    public void deleteCommentById(Long id) {
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new EntityNotFound("Comment not found"));
        commentRepo.delete(comment);
    }
}
