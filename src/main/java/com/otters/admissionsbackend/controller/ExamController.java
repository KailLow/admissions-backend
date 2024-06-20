package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.dto.ExamDTO;
import com.otters.admissionsbackend.model.Exam;
import com.otters.admissionsbackend.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    @PostMapping("")
    public ResponseEntity<?> post (
            @RequestBody ExamDTO exam
    ) {
        return ResponseEntity.ok(examService.add(exam));
    }

    @GetMapping("")
    public ResponseEntity<List<?>> getAll(@RequestParam(defaultValue = "") String name) {
        return ResponseEntity.ok(examService.findAll(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExamById(@PathVariable String id) {
        return ResponseEntity.ok(examService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExamById(@PathVariable String id) {
        examService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExam(@PathVariable String id, @RequestBody ExamDTO dto) {
        return ResponseEntity.ok(examService.update(dto, id));
    }
}
