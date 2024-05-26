package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.Exam;
import com.otters.admissionsbackend.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, String> {
    public Optional<Exam> findByName(String name);
    public Optional<Exam> findById(String Id);
}
