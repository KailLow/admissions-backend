package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.Notification;
import com.otters.admissionsbackend.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService service;

    @GetMapping("/notification")
    public ResponseEntity<List<Notification>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/notification/{id}")
    public ResponseEntity<Notification> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("admin/notification")
    public ResponseEntity<Notification> add(
            @RequestBody Notification request
    ) {
        return ResponseEntity.ok(service.add(request));
    }

    @DeleteMapping("admin/notification/{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("admin/notification/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody Notification request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }
}
