package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.Post;
import com.otters.admissionsbackend.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Post add(Post request) {
        return repository.save(request);
    }

    public void delete(String id) {
        Optional<Post> Post = repository.findById(id);
        if (Post.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        repository.deleteById(id);
    }

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post findById(String id) {
        return repository.findById(id).orElseThrow();
    }

    public Post update(String id, Post request) {
        Optional<Post> oldPost = repository.findById(id);
        if (oldPost.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        Post Post = new Post();
        Post.setContent(request.getContent());
        Post.setTitle(request.getTitle());

        return repository.save(Post);
    }
}
