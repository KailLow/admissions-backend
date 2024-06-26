package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.dto.ExamRoomDTO;
import com.otters.admissionsbackend.model.ExamRoom;
import com.otters.admissionsbackend.model.request.ExamRoomRequest;
import com.otters.admissionsbackend.service.ExamRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam_room")
@RequiredArgsConstructor
public class ExamRoomController {
    private final ExamRoomService service;


    @GetMapping("")
    public ResponseEntity<List<ExamRoom>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> add(
            @RequestBody ExamRoomDTO request
            ) {
        return ResponseEntity.ok(service.add(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody ExamRoomDTO request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable String id
    ) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}
