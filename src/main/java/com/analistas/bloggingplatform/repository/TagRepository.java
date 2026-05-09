package com.analistas.bloggingplatform.repository;

import com.analistas.bloggingplatform.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
