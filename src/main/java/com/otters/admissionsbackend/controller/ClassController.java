package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.dto.ClassDTO;
import com.otters.admissionsbackend.model.MajorClass;
import com.otters.admissionsbackend.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/major_class")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;

    @PostMapping("")
    public ResponseEntity<MajorClass> add(
            @RequestBody ClassDTO majorClass
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
    public ResponseEntity<MajorClass> updateClass(@PathVariable String id, @RequestBody ClassDTO majorClass) {
        return ResponseEntity.ok(classService.update(id, majorClass));
    }
}
