package com.analistas.bloggingplatform.repository;

import com.analistas.bloggingplatform.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
