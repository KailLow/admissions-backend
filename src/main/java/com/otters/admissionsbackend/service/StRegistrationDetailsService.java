package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.StRegistrationDetails;
import com.otters.admissionsbackend.repository.StRegistrationDetailsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StRegistrationDetailsService {
    private final StRegistrationDetailsRepository repository;

    public StRegistrationDetailsService(StRegistrationDetailsRepository repository) {
        this.repository = repository;
    }

    public StRegistrationDetails add(StRegistrationDetails details) {
        return repository.save(details);
    }

    public List<StRegistrationDetails> findAll() {
        return repository.findAll();
    }

    public StRegistrationDetails findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StRegistrationDetails not found"));
    }

    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "StRegistrationDetails not found");
        }
        repository.deleteById(id);
    }
}
