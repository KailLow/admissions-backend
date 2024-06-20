package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.dto.ProfileDTO;
import com.otters.admissionsbackend.model.AuthenticationResponse;
import com.otters.admissionsbackend.model.Profile;
import com.otters.admissionsbackend.model.User;
import com.otters.admissionsbackend.model.request.AuthenticationRequest;
import com.otters.admissionsbackend.model.request.RegisterRequest;
import com.otters.admissionsbackend.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/register/student")
    public ResponseEntity<AuthenticationResponse> studentRegister(
            @RequestBody ProfileDTO request
            ) {
        return ResponseEntity.ok(authenticationService.studentRegister(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return authenticationService.refreshToken(request, response);
    }

//    @GetMapping("/demo")
//    public ResponseEntity<String> demo() {
//        return ResponseEntity.ok("Hello from secured url");
//    }

    @GetMapping("/admin/user")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(authenticationService.getAll());
    }
}
