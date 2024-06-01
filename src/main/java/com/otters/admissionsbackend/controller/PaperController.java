package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.paper.Paper;
import com.otters.admissionsbackend.model.request.PaperRequest;
import com.otters.admissionsbackend.model.response.PaperResponse;
import com.otters.admissionsbackend.service.PaperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paper")
public class PaperController {
    private final PaperService service;

    public PaperController(PaperService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<?> add(
            @RequestBody PaperRequest request
            ) {
        return ResponseEntity.ok(service.add(request));
    }

    @GetMapping("")
    public ResponseEntity<List<PaperResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paper> findById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paper> update(
            @PathVariable String id,
            @RequestBody PaperRequest request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }
}
