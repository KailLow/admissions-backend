package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.exceptionHandler.Error;
import com.otters.admissionsbackend.model.MajorClass;
import com.otters.admissionsbackend.model.Registrations;
import com.otters.admissionsbackend.model.SubjectSet;
import com.otters.admissionsbackend.model.request.RegistrationRequest;
import com.otters.admissionsbackend.repository.ClassRepository;
import com.otters.admissionsbackend.repository.RegistrationsRepository;
import com.otters.admissionsbackend.repository.SubjectSetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationsService {
    private final RegistrationsRepository repository;
    private final SubjectSetRepository subjectSetRepository;
    private final ClassRepository classRepository;

    public RegistrationsService(RegistrationsRepository repository, SubjectSetRepository subjectSetRepository, ClassRepository classRepository) {
        this.repository = repository;
        this.subjectSetRepository = subjectSetRepository;
        this.classRepository = classRepository;
    }

    public Registrations add(RegistrationRequest request) {
        Optional<MajorClass> classOptional = classRepository.findById(request.getClassId());
        if (classOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Class not existed").toString()
            );
        Optional<SubjectSet> subjectSet = subjectSetRepository.findById(request.getSubjectSetId());
        if (subjectSet.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Subject set not existed").toString()
            );
        Registrations registration = new Registrations();
        registration.setMajorClass(classOptional.get());
        registration.setSubjectSet(subjectSet.get());
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

//    public List<Registrations> findByMajorClass(String classId) {
//        Optional<MajorClass> majorClass = classRepository.findById(classId);
//        if (majorClass.isEmpty())
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, new Error("Class not existed").toString()
//            );
//
//        return (repository.findByMajorClass(classId));
//    }
}
