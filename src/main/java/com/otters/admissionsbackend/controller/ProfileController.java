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
    public List<Profile> getAllProfile(@RequestParam(defaultValue = "") String name) {
        return profileService.findByName(name);
    }
}
