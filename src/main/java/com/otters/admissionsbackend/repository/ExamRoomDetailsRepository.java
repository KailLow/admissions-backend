package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.ExamRoomDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRoomDetailsRepository extends JpaRepository<ExamRoomDetails, String> {
}