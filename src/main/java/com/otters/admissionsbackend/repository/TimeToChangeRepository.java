package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.TimeToChange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeToChangeRepository extends JpaRepository<TimeToChange, String> {
}
