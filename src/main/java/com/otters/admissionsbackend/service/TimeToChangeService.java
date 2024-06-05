package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.TimeToChange;
import com.otters.admissionsbackend.repository.TimeToChangeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TimeToChangeService {
    private final TimeToChangeRepository repository;

    public TimeToChangeService(TimeToChangeRepository repository) {
        this.repository = repository;
    }

    public TimeToChange add(TimeToChange timeToChange) {
        TimeToChange time = new TimeToChange();
        time = (TimeToChange) timeToChange.clone();
        return repository.save(time);
    }

    public List<TimeToChange> findAll() {
        return repository.findAll();
    }

    public TimeToChange findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TimeToChange not found"));
    }

    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TimeToChange not found");
        }
        repository.deleteById(id);
    }
}
