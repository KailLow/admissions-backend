package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.Post;
import com.otters.admissionsbackend.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping("/post")
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("admin/post")
    public ResponseEntity<Post> add(
            @RequestBody Post request
    ) {
        return ResponseEntity.ok(service.add(request));
    }

    @DeleteMapping("admin/post/{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("admin/post/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody Post request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }
}
