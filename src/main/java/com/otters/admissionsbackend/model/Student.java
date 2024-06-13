package com.otters.admissionsbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otters.admissionsbackend.model.paper.Paper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToOne
    @JoinColumn(name = "pass_registration_id")
    private Registrations passed;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties(value = {"stRegistrationDetails"})
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "student")
    private List<StRegistrationDetails> stRegistrationDetails;

    @JsonIgnoreProperties(value = {"papers"})
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "student")
    private List<Paper> papers;
}
