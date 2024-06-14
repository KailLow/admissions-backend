package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.Room;
import com.otters.admissionsbackend.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository repository;

    public Room add(Room room) {
        Optional<Room> roomOptional = repository.findByName(room.getName());
        if (roomOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Room has been existed").toString());
        }

        return repository.save(room);
    }

    public void deleteRoomById(
            @PathVariable String id) {
        repository.deleteById(id);
    }

    public List<Room> getAllRooms() {
        return repository.findAll();
    }

    public Room findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));
    }

    public Room update(String id, Room room) {
        Room existingRoom = findById(id);
        existingRoom.setName(room.getName());
        existingRoom.setMaxContain(room.getMaxContain());
        return repository.save(existingRoom);
    }
}
