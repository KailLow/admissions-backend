package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.dto.BenchMarkDTO;
import com.otters.admissionsbackend.model.BenchMark;
import com.otters.admissionsbackend.model.request.BenchMarkRequest;
import com.otters.admissionsbackend.service.BenchMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bench_mark")
@RequiredArgsConstructor
public class BenchMarkController {
    private final BenchMarkService service;

    @PostMapping("")
    public ResponseEntity<?> add (
            @RequestBody BenchMarkDTO request
            ) {
        return ResponseEntity.ok(service.add(request));
    }

    @GetMapping
    public ResponseEntity<List<BenchMark>> getAllBenchMarks() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BenchMark> getBenchMarkById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBenchMarkById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BenchMark> updateBenchMark(@PathVariable String id, @RequestBody BenchMarkDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }
}
