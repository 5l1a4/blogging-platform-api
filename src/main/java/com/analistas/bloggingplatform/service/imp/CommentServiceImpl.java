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
    public List<CommentResponse> getCommentsByPostId(Long postId) {
        validatePostExists(postId);
        return commentRepo.findByPostId(postId).stream().map(commentMapper::toResponse).toList();
    }

    @Override
    public CommentResponse getCommentById(Long postId, Long id) {
        return commentMapper.toResponse(getCommentFromPost(postId, id));
    }

    @Override
    public CommentResponse createComment(Long postId, CommentRequest commentRequest) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new EntityNotFound("Post not found"));
        Comment commentCreate = commentMapper.toEntity(commentRequest);
        commentCreate.setPost(post);
        return commentMapper.toResponse(commentRepo.save(commentCreate));
    }

    @Override
    public CommentResponse updateComment(Long postId, Long id, CommentRequest commentRequest) {
        Comment comment = getCommentFromPost(postId, id);
        comment.setAuthor(commentRequest.getAuthor());
        comment.setText(commentRequest.getText());
        return commentMapper.toResponse(commentRepo.save(comment));
    }

    @Override
    public void deleteCommentById(Long postId, Long id) {
        Comment comment = getCommentFromPost(postId, id);
        commentRepo.delete(comment);
    }

    private void validatePostExists(Long postId) {
        if (!postRepo.existsById(postId)) {
            throw new EntityNotFound("Post not found");
        }
    }

    private Comment getCommentFromPost(Long postId, Long id) {
        return commentRepo.findByIdAndPostId(id, postId)
                .orElseThrow(() -> new EntityNotFound("Comment not found"));
    }
}
