package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.paper.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaperRepository extends JpaRepository<Paper, String> {
    List<Paper> findByStudentId(String studentId);
    List<Paper> findBySubjectId(String subjectId);

    Optional<Paper> findById(String id);
}
