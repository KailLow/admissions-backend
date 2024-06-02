package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.exceptionHandler.Error;
import com.otters.admissionsbackend.model.Registrations;
import com.otters.admissionsbackend.model.StRegistrationDetails;
import com.otters.admissionsbackend.model.Student;
import com.otters.admissionsbackend.model.request.StRegistrationRequest;
import com.otters.admissionsbackend.repository.RegistrationsRepository;
import com.otters.admissionsbackend.repository.StRegistrationDetailsRepository;
import com.otters.admissionsbackend.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StRegistrationDetailsService {
    private final StRegistrationDetailsRepository repository;
    private final StudentRepository studentRepository;
    private final RegistrationsRepository registrationsRepository;

    public StRegistrationDetailsService(StRegistrationDetailsRepository repository, StudentRepository studentRepository, RegistrationsRepository registrationsRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.registrationsRepository = registrationsRepository;
    }

    public StRegistrationDetails add(StRegistrationRequest request) {
        Optional<Student> student = studentRepository.findById(request.getStudentId());
        if (student.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Student not existed").toString()
            );
        Optional<Registrations> registrations = registrationsRepository.findById(request.getRegistrationId());
        if (registrations.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Regitration not existed").toString()
            );

        StRegistrationDetails details = new StRegistrationDetails();
        details.setNumericalOrder(request.getNumericalOrder());
        details.setStudent(student.get());
        details.setRegistration(registrations.get());
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
