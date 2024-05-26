package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.request.BenchMarkRequest;
import com.otters.admissionsbackend.service.BenchMarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bench_mark")
public class BenchMarkController {
    private final BenchMarkService service;

    public BenchMarkController(BenchMarkService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<?> add (
            @RequestBody BenchMarkRequest request
            ) {
        return ResponseEntity.ok(service.add(request));
    }
}
