package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, String> {
    public Optional<Subject> findByName(String name);
    public Optional<Subject> findById(String id);
    Page<Subject> findAllByOrderByName(Pageable pageable);
//    public List<Subject> findByNameContainingIgnoreCaseAndIsStopped(String name);
}
