package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.dto.StudentPaperScoreDTO;
import com.otters.admissionsbackend.model.paper.Paper;
import com.otters.admissionsbackend.model.request.PaperRequest;
import com.otters.admissionsbackend.model.response.PaperResponse;
import com.otters.admissionsbackend.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paper")
@RequiredArgsConstructor
public class PaperController {
    private final PaperService service;

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

    @GetMapping("/get_pass")
    public ResponseEntity<List<StudentPaperScoreDTO>> findTop(
            @RequestParam(defaultValue = "24") int limit
    ) {
        return ResponseEntity.ok(service.getTopStudentsByTotalPaperScore(24));
    }
}
