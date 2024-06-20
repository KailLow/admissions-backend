package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.dto.ExamDTO;
import com.otters.admissionsbackend.mapper.DTOtoEntityMapper;
import com.otters.admissionsbackend.model.Exam;
import com.otters.admissionsbackend.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository repository;

    private final DTOtoEntityMapper mapper;

    public Exam add(ExamDTO dto) {
        Optional<Exam> examOptional = repository.findByName(dto.getName());
        if (examOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, new Error("Exam existed").toString()
            );
        }

        Exam exam = new Exam();
        exam.setName(dto.getName());
        exam.setYear(dto.getYear());
        return repository.save(exam);
    }

    public List<Exam> findAll(String name) {
        return repository.findAll();
    }

    public Exam findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam not found"));
    }

    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam not found");
        }
        repository.deleteById(id);
    }

    public Exam update(ExamDTO dto, String id) {
        Optional<Exam> examOptional = repository.findById(id);
        if (examOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam not found");
        }
        Exam exam = examOptional.get();
        mapper.updateExamFromDto(dto, exam);
        return repository.save(exam);
    }

//    public Exam update(String id, Exam updatedExam) {
//        Exam existingExam = findById(id);
//        existingExam.setName(updatedExam.getName());
//        existingExam.setYear(updatedExam.getYear());
//        return repository.save(existingExam);
//    }
}
