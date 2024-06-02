package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.Registrations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationsRepository extends JpaRepository<Registrations, String> {
}