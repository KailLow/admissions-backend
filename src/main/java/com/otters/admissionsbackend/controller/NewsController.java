package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.News;
import com.otters.admissionsbackend.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsController {
    private final NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }

    @GetMapping("/news")
    public ResponseEntity<List<News>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<News> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("admin/news")
    public ResponseEntity<News> add(
            @RequestBody News request
    ) {
        return ResponseEntity.ok(service.add(request));
    }

    @DeleteMapping("admin/news/{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("admin/news/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody News request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }
}
