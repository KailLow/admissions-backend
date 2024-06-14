package com.otters.admissionsbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "registrations")
@Getter
@Setter
public class Registrations {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "subject_set_id")
    private SubjectSet subjectSet;

    @ManyToOne
    @JoinColumn(name = "class_code")
    private MajorClass majorClass;

    @JsonIgnoreProperties(value = {"stRegistrationDetails"})
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "registration")
    private List<StRegistrationDetails> stRegistrationDetails;
}
