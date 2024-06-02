package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.StRegistrationDetails;
import com.otters.admissionsbackend.service.StRegistrationDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stRegistrationDetails")
public class StRegistrationDetailsController {
    private final StRegistrationDetailsService service;

    public StRegistrationDetailsController(StRegistrationDetailsService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<StRegistrationDetails> post(@RequestBody StRegistrationDetails details) {
        return ResponseEntity.ok(service.add(details));
    }

    @GetMapping("")
    public List<StRegistrationDetails> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StRegistrationDetails> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
