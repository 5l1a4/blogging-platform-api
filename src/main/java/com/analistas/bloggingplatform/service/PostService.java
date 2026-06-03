package com.analistas.bloggingplatform.service;

import com.analistas.bloggingplatform.domain.dto.PostRequest;
import com.analistas.bloggingplatform.domain.dto.PostResponse;
import com.analistas.bloggingplatform.domain.entity.Post;

import java.util.List;

public interface PostService {

    List<PostResponse> getPosts();

    PostResponse getPostById(Long id);

    PostResponse createPost(PostRequest postRequest);

    PostResponse updatePost(Long id, PostRequest postRequest);

    List<PostResponse> searchPostByTerm (String term);

    void deletePost(Long id);
}
