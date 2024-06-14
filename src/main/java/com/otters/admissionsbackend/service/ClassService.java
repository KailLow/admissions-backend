package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.dto.BenchMarkDTO;
import com.otters.admissionsbackend.dto.ClassDTO;
import com.otters.admissionsbackend.model.MajorClass;
import com.otters.admissionsbackend.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassService {
    private final ClassRepository repository;

    public MajorClass add(ClassDTO request) {
        Optional<MajorClass> examOptional = repository.findByName(request.getName());
        if (examOptional.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, new Error("Major Class existed").toString()
            );
        }

        MajorClass majorClass = new MajorClass();
        majorClass.setName(request.getName());
        majorClass.setQuotas(request.getQuotas());
        majorClass.setYear(request.getYear());

        return repository.save(majorClass);
    }

    public List<MajorClass> findAll(String name) {
        return repository.findAll();
    }

    public MajorClass findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Major Class not found"));
    }

    public MajorClass update(String id, ClassDTO majorClass) {
        MajorClass existingClass = findById(id);
        existingClass.setName(majorClass.getName());
        existingClass.setYear(majorClass.getYear());
        existingClass.setQuotas(majorClass.getQuotas());
        return repository.save(existingClass);
    }

    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Major Class not found");
        }
        repository.deleteById(id);
    }
}
