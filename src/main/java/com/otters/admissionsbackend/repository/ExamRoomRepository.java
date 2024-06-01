package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.ExamRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamRoomRepository extends JpaRepository<ExamRoom, String> {
    public Optional<ExamRoom> findById(String id);
}
