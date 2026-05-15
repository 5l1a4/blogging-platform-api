package com.analistas.bloggingplatform.repository;

import com.analistas.bloggingplatform.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);

    Optional<Comment> findByIdAndPostId(Long id, Long postId);
}
