package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.AuthenticationResponse;
import com.otters.admissionsbackend.model.Profile;
import com.otters.admissionsbackend.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProfileService {
    private final ProfileRepository repository;

    public ProfileService(ProfileRepository profileRepository) {
        this.repository = profileRepository;
    }

    public Profile addProfile(Profile profile) throws Exception {
        if(repository.findByEmail(profile.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Students has been existed").toString());
        }

        Profile profileStudent = (Profile) profile.clone();

        return repository.save(profileStudent);
    }

    public List<Profile> findByName(String name) {
        return repository.findByFullNameContainingIgnoreCase(name);
    }

    public Profile findByEmail(String email) throws Exception {
        return repository.findByEmail(email).get();
    }
}
