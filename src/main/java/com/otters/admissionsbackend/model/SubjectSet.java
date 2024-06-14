package com.otters.admissionsbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "subject_set")
public class SubjectSet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subject> subjects;

    @JsonIgnoreProperties(value = {"registrations"})
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "subjectSet")
    private List<Registrations> registrations;
}
