package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.ExamRoomDetails;
import com.otters.admissionsbackend.model.request.ExRoomDtlsRequest;
import com.otters.admissionsbackend.service.ExamRoomDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examRoomDetails")
public class ExamRoomDetailsController {
    private final ExamRoomDetailsService service;

    public ExamRoomDetailsController(ExamRoomDetailsService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<ExamRoomDetails> post(@RequestBody ExRoomDtlsRequest details) {
        return ResponseEntity.ok(service.add(details));
    }

    @GetMapping("")
    public List<ExamRoomDetails> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamRoomDetails> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
