package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.SubjectSet;
import com.otters.admissionsbackend.service.SubjectSetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjectSets")
public class SubjectSetController {
    private final SubjectSetService service;

    public SubjectSetController(SubjectSetService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<SubjectSet> post(@RequestBody SubjectSet subjectSet) {
        return ResponseEntity.ok(service.add(subjectSet));
    }

    @GetMapping("")
    public List<SubjectSet> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectSet> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
