package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.Exam;
import com.otters.admissionsbackend.repository.ExamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {
    private final ExamRepository repository;

    public ExamService(ExamRepository repository) {
        this.repository = repository;
    }

    public Exam add(Exam exam) {
        Optional<Exam> examOptional = repository.findByName(exam.getName());
        if (examOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, new Error("Exam existed").toString()
            );
        }

        return repository.save(exam);
    }

    public List<Exam> findAll(String name) {
        return repository.findAll();
    }
}
