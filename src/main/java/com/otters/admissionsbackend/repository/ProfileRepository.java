package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, String> {
    Optional<Profile> findByEmail(String email);

    List<Profile> findByFullNameContainingIgnoreCase(String name);
    Optional<Profile> findById(String id);
}
