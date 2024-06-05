package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.News;
import com.otters.admissionsbackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, String> {
    public Optional<Post> findById(String id);
}
