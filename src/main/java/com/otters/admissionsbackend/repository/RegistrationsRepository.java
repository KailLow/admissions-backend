package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.Registrations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationsRepository extends JpaRepository<Registrations, String> {
//    public List<Registrations> findByMajorClass(String classId);
}