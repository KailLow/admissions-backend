package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.ExamRoomDetails;
import com.otters.admissionsbackend.repository.ExamRoomDetailsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ExamRoomDetailsService {
    private final ExamRoomDetailsRepository repository;

    public ExamRoomDetailsService(ExamRoomDetailsRepository repository) {
        this.repository = repository;
    }

    public ExamRoomDetails add(ExamRoomDetails details) {
        return repository.save(details);
    }

    public List<ExamRoomDetails> findAll() {
        return repository.findAll();
    }

    public ExamRoomDetails findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ExamRoomDetails not found"));
    }

    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ExamRoomDetails not found");
        }
        repository.deleteById(id);
    }
}
