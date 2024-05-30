package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.Subject;
import com.otters.admissionsbackend.repository.SubjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository repository;

    public SubjectService(SubjectRepository repository) {
        this.repository = repository;
    }

    public List<Subject> findByName(String name) {
        return repository.findAll();
    }

    public Page<Subject> getAllProfiles(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        return repository.findAllByOrderByName(pageRequest);
    }

    public Subject add(Subject subjectDTO){
        Optional<Subject> subjectOptional = repository.findByName(subjectDTO.getName());
        if (subjectOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, new Error("Subject existed").toString()
            );
        }

        Subject subject = new Subject();
        subject = (Subject) subjectDTO.clone();
        return repository.save(subject);
    }

    public Subject update(Subject subject, String id) {
        Optional<Subject> subjectOptional = repository.findById(id);
        if(subjectOptional.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Subject not existed");
        }
        repository.deleteById(id);
        return repository.save(subject);
    }
    public Subject findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found"));
    }

    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }
        repository.deleteById(id);
    }
}
