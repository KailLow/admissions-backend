package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.Profile;
import com.otters.admissionsbackend.model.Student;
import com.otters.admissionsbackend.model.User;
import com.otters.admissionsbackend.repository.ProfileRepository;
import com.otters.admissionsbackend.repository.StudentRepository;
import com.otters.admissionsbackend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;
    private ProfileRepository profileRepository;
    private UserRepository userRepository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student add(String profileId, String userId) {
        Optional<Profile> profileOptional = profileRepository.findById(profileId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (profileOptional.isEmpty() || userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID(s) provided");
        }

        Student student = new Student();
        student.setProfile(profileOptional.get());
        student.setUser(userOptional.get());
        student.setPassed(null);
        return repository.save(student);
    }

    public Page<Student> getAll(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        return repository.findAllByOrderByProfile(pageRequest);
    }

    public List<Student> findAll(){
        return repository.findAll();
    }

    public Student findById(String id) {
        return repository.findById(id).get();
    }
}
