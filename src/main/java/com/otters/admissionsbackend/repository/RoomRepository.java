package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, String> {
    Optional<Room> findByName(String name);
    Optional<Room> findById(String id);
}
