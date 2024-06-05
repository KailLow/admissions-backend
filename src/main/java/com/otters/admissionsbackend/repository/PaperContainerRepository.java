package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.paper.PaperContainers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperContainerRepository extends JpaRepository<PaperContainers, String> {
}
