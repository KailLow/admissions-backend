package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.Profile;
import com.otters.admissionsbackend.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("")
    public ResponseEntity<Profile> postProfile(
            @RequestBody Profile profile
    ) throws Exception {
        return ResponseEntity.ok(profileService.addProfile(profile));
    }

    @GetMapping("/all-profile")
    public ResponseEntity<List<Profile>> getAllProfile(@RequestParam(defaultValue = "") String name) {
        return ResponseEntity.ok(profileService.findByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(
            @PathVariable String id
    ) throws Exception {
        Profile profile = profileService.findById(id);
        return ResponseEntity.ok(profile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        profileService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> update(
            @PathVariable String id,
            @RequestBody Profile request
    ) {
        return ResponseEntity.ok(profileService.update(id, request));
    }
}
