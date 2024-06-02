package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.*;
import com.otters.admissionsbackend.repository.ProfileRepository;
import com.otters.admissionsbackend.repository.StudentRepository;
import com.otters.admissionsbackend.repository.TokenRepository;
import com.otters.admissionsbackend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    private final ProfileRepository profileRepository;
    private final StudentRepository studentRepository;

    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager, ProfileRepository profileRepository, StudentRepository studentRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
        this.profileRepository = profileRepository;
        this.studentRepository = studentRepository;
    }

    public AuthenticationResponse register(User request) {
        // check if user already exist. if exist than authenticate the user
        if(repository.findByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, null,"User already exist", null);
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        user = repository.save(user);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(accessToken, refreshToken,user.getRole() + " registration was successful", user.getRole());
    }

    public AuthenticationResponse studentRegister(Profile request) {
        if (repository.findByUsername(request.getEmail()).isPresent()) {
            return new AuthenticationResponse(null, null,"User already exist", null);
        }
        User user = new User();
        user.setUsername(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getNumberId()));
        user.setRole(Role.STUDENT);

        Profile profile = (Profile) request.clone();
        profileRepository.save(profile);

        user = repository.save(user);

        Student student = new Student();
        student.setProfile(profile);
        student.setPassed(null);
        student.setUser(user);
        studentRepository.save(student);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(accessToken, refreshToken,user.getRole() + " registration was successful", user.getRole());
    }

    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(accessToken, refreshToken, user.getRole() + " login was successful", user.getRole());
    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllAccessTokensByUser(user.getId());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }

    private void saveUserToken(String accessToken, String refreshToken, User user) {
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    public ResponseEntity refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) {
        // extract the token from authorization header
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7);

        // extract username from token
        String username = jwtService.extractUsername(token);

        // check if the user exist in database
        User user = repository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("No user found"));

        // check if the token is valid
        if(jwtService.isValidRefreshToken(token, user)) {
            // generate access token
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            revokeAllTokenByUser(user);
            saveUserToken(accessToken, refreshToken, user);

            return new ResponseEntity(new AuthenticationResponse(accessToken, refreshToken, "New token generated", user.getRole()), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);

    }
}
