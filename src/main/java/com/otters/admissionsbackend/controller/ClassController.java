package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.MajorClass;
import com.otters.admissionsbackend.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/major_class")
public class ClassController {
    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping("")
    public ResponseEntity<MajorClass> add(
            @RequestBody MajorClass majorClass
    ) {
        return ResponseEntity.ok(classService.add(majorClass));
    }

    @GetMapping("")
    public List<MajorClass> getAll(@RequestParam(defaultValue = "") String name) {
        return classService.findAll(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MajorClass> getClassById(@PathVariable String id) {
        return ResponseEntity.ok(classService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassById(@PathVariable String id) {
        classService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MajorClass> updateClass(@PathVariable String id, @RequestBody MajorClass majorClass) {
        return ResponseEntity.ok(classService.update(id, majorClass));
    }
}
