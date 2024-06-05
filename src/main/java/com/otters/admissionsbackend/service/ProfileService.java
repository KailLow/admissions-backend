package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.command.ProfileCommand;
import com.otters.admissionsbackend.exceptionHandler.Error;
import com.otters.admissionsbackend.model.AuthenticationResponse;
import com.otters.admissionsbackend.model.Profile;
import com.otters.admissionsbackend.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository repository;
    private static final ProfileCommand command = new ProfileCommand();

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

    public Profile findByEmail(String email) {
        Optional<Profile> profile = repository.findByEmail(email);
        if (profile.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not existed");
        return profile.get();
    }

    public Profile findById(String id) {
        return repository.findById(id).get();
    }

    public Profile update(String id, Profile request) {
        Optional<Profile> profileOptional = repository.findById(id);
        if (profileOptional.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Profile not existed").toString()
            );
        }
        Profile profile = profileOptional.get();
        command.copy(profile, request);

        return repository.save(profile);
    }

    public void remove(String id) {
        Optional<Profile> profileOptional = repository.findById(id);
        if (profileOptional.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Profile not existed").toString()
            );
        }
        repository.deleteById(id);
    }
}
