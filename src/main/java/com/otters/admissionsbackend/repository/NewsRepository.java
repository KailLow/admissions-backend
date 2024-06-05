package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, String> {
    public Optional<News> findById(String id);
}
