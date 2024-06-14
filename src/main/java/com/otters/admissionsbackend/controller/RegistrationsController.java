package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.Registrations;
import com.otters.admissionsbackend.model.request.RegistrationRequest;
import com.otters.admissionsbackend.service.RegistrationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
@RequiredArgsConstructor
public class RegistrationsController {
    private final RegistrationsService service;

    @PostMapping("")
    public ResponseEntity<Registrations> post(@RequestBody RegistrationRequest registration) {
        return ResponseEntity.ok(service.add(registration));
    }

    @GetMapping("")
    public ResponseEntity<List<Registrations>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registrations> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

//    @GetMapping("/class/{id}")
//    public ResponseEntity<List<Registrations>> findByMajorClass(
//            @PathVariable String id
//    ) {
//        return ResponseEntity.ok(service.findByMajorClass(id));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
