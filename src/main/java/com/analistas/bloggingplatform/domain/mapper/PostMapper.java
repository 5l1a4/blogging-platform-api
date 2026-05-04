package com.analistas.bloggingplatform.domain.mapper;

import com.analistas.bloggingplatform.domain.dto.PostRequest;
import com.analistas.bloggingplatform.domain.dto.PostResponse;
import com.analistas.bloggingplatform.domain.entity.Post;
import com.analistas.bloggingplatform.exception.EntityNotFound;
import com.analistas.bloggingplatform.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    private final CategoryRepository cateRepo;

    public PostMapper(CategoryRepository cateRepo) {
        this.cateRepo = cateRepo;
    }

    public PostResponse toResponse(Post post){
        return new PostResponse(
                post.getTitle(),
                post.getContent(),
                post.getSubtitle(),
                post.getCreatedAt(),
                post.getCategory().getName()
        );
    }

    public Post toEntity(PostRequest request){
        return new Post(
                request.getTitle(),
                request.getContent(),
                request.getSubtitle(),
                cateRepo.findById(request.getIdCategory()).orElseThrow(() -> new EntityNotFound("Category not found"))
        );
    }

}
