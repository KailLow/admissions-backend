package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.SubjectSet;
import com.otters.admissionsbackend.repository.SubjectSetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SubjectSetService {
    private final SubjectSetRepository repository;

    public SubjectSetService(SubjectSetRepository repository) {
        this.repository = repository;
    }

    public SubjectSet add(SubjectSet subjectSet) {
        return repository.save(subjectSet);
    }

    public List<SubjectSet> findAll() {
        return repository.findAll();
    }

    public SubjectSet findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubjectSet not found"));
    }

    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SubjectSet not found");
        }
        repository.deleteById(id);
    }
}
