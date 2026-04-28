package com.analistas.bloggingplatform.service.imp;

import com.analistas.bloggingplatform.domain.dto.PostRequest;
import com.analistas.bloggingplatform.domain.dto.PostResponse;
import com.analistas.bloggingplatform.domain.entity.Post;
import com.analistas.bloggingplatform.domain.mapper.PostMapper;
import com.analistas.bloggingplatform.repository.PostRepository;
import com.analistas.bloggingplatform.service.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepo;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepo, PostMapper postMapper) {
        this.postRepo = postRepo;
        this.postMapper = postMapper;
    }

    @Override
    public List<PostResponse> getPosts() {
        List<Post> posts = postRepo.findAll();
        List<PostResponse> postReponses = new ArrayList<>();
        posts.forEach(post -> {
            postReponses.add(postMapper.toResponse(post));
        });
        return postReponses;
    }

    @Override
    public PostResponse getPostById(Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("post not found"));
        return postMapper.toResponse(post);
    }

    @Override
    public PostResponse createPost(PostRequest postRequest) {
        Post post = postMapper.toEntity(postRequest);
        return postMapper.toResponse(postRepo.save(post));
    }

    @Override
    public PostResponse updatePost(Long id, PostRequest postRequest) {
        Post post = postRepo.findById(id).orElseThrow();
        post.setTitle(postRequest.getTitle());
        post.setSubtitle(postRequest.getSubtitle());
        post.setContent(postRequest.getContent());
        return postMapper.toResponse(postRepo.save(post));
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("post not found"));
        postRepo.delete(post);
    }
}
