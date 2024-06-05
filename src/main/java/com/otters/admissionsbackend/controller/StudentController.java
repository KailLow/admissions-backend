package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.Profile;
import com.otters.admissionsbackend.model.Student;
import com.otters.admissionsbackend.model.User;
import com.otters.admissionsbackend.service.AuthenticationService;
import com.otters.admissionsbackend.service.ProfileService;
import com.otters.admissionsbackend.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Student>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "24") int limit
    ) {
        return ResponseEntity.ok(service.getAll(page, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

//    @PostMapping("/register")
//    public ResponseEntity<Student> add (
//            @RequestBody Profile request
//            ) throws Exception {
//        Profile profile = profileService.addProfile(request);
//        User user = authenticationService.studentRegister(request.getEmail(), request.getNumberId());
//        return ResponseEntity.ok(service.add(profile.getId(), user.getId()));
//    }

    @GetMapping("/students")
    public List<Student> getStudentsOrderedByName() {
        return service.getStudentsOrderedByName();
    }
}
