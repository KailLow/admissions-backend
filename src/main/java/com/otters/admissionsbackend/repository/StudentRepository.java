package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    public Optional<Student> findById(String id);
    Page<Student> findAllByOrderByProfile(Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.user.id = :userId")
    Student findByUserId(@Param("userId") String userId);
    List<Student> findAllByOrderByProfileFullNameAsc();
}
