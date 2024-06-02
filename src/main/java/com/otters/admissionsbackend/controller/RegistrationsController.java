package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.Registrations;
import com.otters.admissionsbackend.service.RegistrationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationsController {
    private final RegistrationsService service;

    public RegistrationsController(RegistrationsService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Registrations> post(@RequestBody Registrations registration) {
        return ResponseEntity.ok(service.add(registration));
    }

    @GetMapping("")
    public List<Registrations> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registrations> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
