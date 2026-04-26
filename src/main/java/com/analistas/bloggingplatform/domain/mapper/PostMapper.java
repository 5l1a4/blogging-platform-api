package com.analistas.bloggingplatform.domain.mapper;

import com.analistas.bloggingplatform.domain.dto.PostRequest;
import com.analistas.bloggingplatform.domain.dto.PostResponse;
import com.analistas.bloggingplatform.domain.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostResponse toResponse(Post post){
        return new PostResponse(
                post.getTitle(),
                post.getContent(),
                post.getSubtitle(),
                post.getCreatedAt()
        );
    }

    public Post toEntity(PostRequest request){
        return new Post(
                request.getTitle(),
                request.getContent(),
                request.getSubtitle()
        );
    }

}
