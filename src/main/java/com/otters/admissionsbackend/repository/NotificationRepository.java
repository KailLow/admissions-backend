package com.otters.admissionsbackend.repository;

import com.otters.admissionsbackend.model.News;
import com.otters.admissionsbackend.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, String> {
    public Optional<Notification> findById(String id);
}
