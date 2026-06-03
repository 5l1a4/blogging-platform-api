package com.analistas.bloggingplatform.repository;

import com.analistas.bloggingplatform.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{

    @Query("SELECT p FROM Post p WHERE p.title LIKE %:term% OR p.content LIKE %:term% OR p.category.name LIKE %:term%")
    List<Post> searchPostByTerm(@Param("term") String term);
}
