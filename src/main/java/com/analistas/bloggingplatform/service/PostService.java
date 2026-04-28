package com.analistas.bloggingplatform.service;

import com.analistas.bloggingplatform.domain.dto.PostRequest;
import com.analistas.bloggingplatform.domain.dto.PostResponse;

import java.util.List;

public interface PostService {

    List<PostResponse> getPosts();

    PostResponse getPostById(Long id);

    PostResponse createPost(PostRequest postRequest);

    PostResponse updatePost(Long id, PostRequest postRequest);

    void deletePost(Long id);
}
