package com.otters.admissionsbackend.controller;

import com.otters.admissionsbackend.model.Profile;
import com.otters.admissionsbackend.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

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
    ) {
        Profile profile = profileService.findById(id);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Profile> getProfileByEmail(
            @PathVariable String email
    ){
        Profile profile = profileService.findByEmail(email);
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
