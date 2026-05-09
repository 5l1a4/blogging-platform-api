package com.analistas.bloggingplatform.repository;

import com.analistas.bloggingplatform.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
