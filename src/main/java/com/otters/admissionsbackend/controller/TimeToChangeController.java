package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.TimeToChange;
import com.otters.admissionsbackend.model.response.PaperResponse;
import com.otters.admissionsbackend.service.TimeToChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timeToChange")
@RequiredArgsConstructor
public class TimeToChangeController {
    private final TimeToChangeService service;

    @PostMapping("")
    public ResponseEntity<TimeToChange> post(@RequestBody TimeToChange timeToChange) {
        return ResponseEntity.ok(service.add(timeToChange));
    }

    @GetMapping("")
    public ResponseEntity<List<TimeToChange>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeToChange> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
