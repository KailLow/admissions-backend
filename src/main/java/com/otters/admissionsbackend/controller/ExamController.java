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
    public List<Exam> getAll(@RequestParam(defaultValue = "") String name) {
        return examService.findAll(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable String id) {
        return ResponseEntity.ok(examService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExamById(@PathVariable String id) {
        examService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Exam> updateExam(@PathVariable String id, @RequestBody Exam exam) {
//        return ResponseEntity.ok(examService.update(id, exam));
//    }
}
