package com.analistas.bloggingplatform.repository;

import com.analistas.bloggingplatform.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{

}
