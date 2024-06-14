package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.exceptionHandler.Error;
import com.otters.admissionsbackend.model.Subject;
import com.otters.admissionsbackend.model.SubjectSet;
import com.otters.admissionsbackend.model.request.SubjectSetRequest;
import com.otters.admissionsbackend.repository.SubjectRepository;
import com.otters.admissionsbackend.repository.SubjectSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectSetService {
    private final SubjectSetRepository repository;
    private final SubjectRepository subjectRepository;

    public SubjectSet add(SubjectSetRequest subjectSet) {
        List<Subject> subjects = new ArrayList<>();
        for (String subject: subjectSet.getSubjects()
             ) {
            Optional<Subject> subjectOptional = subjectRepository.findById(subject);
            if (subjectOptional.isEmpty())
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,new Error("Subject not found").toString()
                );
            subjects.add(subjectOptional.get());
        }
        SubjectSet subjectSet1 = new SubjectSet();
        subjectSet1.setSubjects(subjects);
        subjectSet1.setName(subjectSet.getName());
        return repository.save(subjectSet1);
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
