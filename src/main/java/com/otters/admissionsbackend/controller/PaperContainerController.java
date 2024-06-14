package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.paper.PaperContainers;
import com.otters.admissionsbackend.model.request.PaperContainerRequest;
import com.otters.admissionsbackend.service.PaperContainerService;
import com.otters.admissionsbackend.service.RoomDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/paper-containers")
@RequiredArgsConstructor
public class PaperContainerController {

    private final PaperContainerService paperContainerService;
    private final RoomDetailsService roomDetailsService;

    @PostMapping
    public ResponseEntity<?> addPaperContainer(@RequestBody PaperContainerRequest request) {
        return ResponseEntity.ok(paperContainerService.add(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaperContainers> getPaperContainerById(@PathVariable String id) {
        return paperContainerService.findById(id)
                .map(paperContainers -> new ResponseEntity<>(paperContainers, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<PaperContainers>> getAllPaperContainers() {
        List<PaperContainers> paperContainers = paperContainerService.findAll();
        return new ResponseEntity<>(paperContainers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaperContainer(@PathVariable String id) {
        try {
            paperContainerService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaperContainer(@PathVariable String id, @RequestBody PaperContainerRequest request) {
        try {
            PaperContainers updatedContainer = paperContainerService.update(id, request);
            return ResponseEntity.ok(updatedContainer);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        }
    }

    @GetMapping("/paper/{id}")
    public ResponseEntity<List<?>> getExamRoomDetails(@PathVariable String id) {
        return ResponseEntity.ok(roomDetailsService.get(id));
    }
}
