package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.Room;
import com.otters.admissionsbackend.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable String id) {
        service.deleteRoomById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = service.getAllRooms();
        return ResponseEntity.ok(rooms);
    }
}
