package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.MajorClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<MajorClass, String> {
    public Optional<MajorClass> findByName(String name);
    public Optional<MajorClass> findById(String id);
    Page<MajorClass> findAllByOrderByName(Pageable pageable);
}
