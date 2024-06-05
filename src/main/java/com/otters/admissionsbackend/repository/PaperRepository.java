package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.dto.StudentPaperScoreDTO;
import com.otters.admissionsbackend.model.paper.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaperRepository extends JpaRepository<Paper, String> {
    List<Paper> findByStudentId(String studentId);
    List<Paper> findBySubjectId(String subjectId);

    Optional<Paper> findById(String id);
    Page<Paper> findByOrderByScoreDesc(Pageable pageable);
    List<Paper> findByOrderByScoreDesc();
    @Query("SELECT new com.otters.admissionsbackend.dto.StudentPaperScoreDTO(" +
            "   p.student.id, " +
            "   SUM(p.score) " +
            ") " +
            "FROM Paper p " +
            "GROUP BY p.student.id " +
            "ORDER BY SUM(p.score) DESC " +
            "LIMIT :limit")
    List<StudentPaperScoreDTO> findTopStudentsByTotalPaperScore(@Param("limit") int limit);
}
