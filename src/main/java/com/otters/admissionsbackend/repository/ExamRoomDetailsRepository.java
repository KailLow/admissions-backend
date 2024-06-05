package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.ExamRoomDetails;
import com.otters.admissionsbackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRoomDetailsRepository extends JpaRepository<ExamRoomDetails, String> {

    List<Student> findStudentsByExamRoomId(String examRoomId);
}