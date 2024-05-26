package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.Room;
import com.otters.admissionsbackend.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<?> add(
            @RequestBody Room room
            ) {
        return ResponseEntity.ok(service.add(room));
    }
}
