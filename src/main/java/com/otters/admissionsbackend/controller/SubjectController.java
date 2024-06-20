package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.Subject;
import com.otters.admissionsbackend.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping("")
    public ResponseEntity<?> post(
            @RequestBody Subject subjectDTO
            ) {
        return ResponseEntity.ok(subjectService.add(subjectDTO));
    }

    @GetMapping("")
//    public List<Subject> getAll(@RequestParam(defaultValue = "") String name) {
//        return subjectService.findByName(name);
//    }
    public ResponseEntity<Page<Subject>> getAllProfiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int limit
    ) {
        Page<Subject> profiles = subjectService.getAllProfiles(page, limit);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public Subject edit(@RequestBody Subject subject, @PathVariable String id) {
        return subjectService.update(subject, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable String id) {
        return ResponseEntity.ok(subjectService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubjectById(@PathVariable String id) {
        subjectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
