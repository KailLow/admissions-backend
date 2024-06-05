package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.Notification;
import com.otters.admissionsbackend.repository.NotificationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public Notification add(Notification request) {
        return repository.save(request);
    }

    public void delete(String id) {
        Optional<Notification> Notification = repository.findById(id);
        if (Notification.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        repository.deleteById(id);
    }

    public List<Notification> findAll() {
        return repository.findAll();
    }

    public Notification findById(String id) {
        return repository.findById(id).orElseThrow();
    }

    public Notification update(String id, Notification request) {
        Optional<Notification> oldNotification = repository.findById(id);
        if (oldNotification.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        Notification Notification = new Notification();
        Notification.setContent(request.getContent());
        Notification.setTitle(request.getTitle());

        return repository.save(Notification);
    }
}
