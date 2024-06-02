package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.Registrations;
import com.otters.admissionsbackend.repository.RegistrationsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RegistrationsService {
    private final RegistrationsRepository repository;

    public RegistrationsService(RegistrationsRepository repository) {
        this.repository = repository;
    }

    public Registrations add(Registrations registration) {
        return repository.save(registration);
    }

    public List<Registrations> findAll() {
        return repository.findAll();
    }

    public Registrations findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registration not found"));
    }

    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registration not found");
        }
        repository.deleteById(id);
    }
}
