package com.otters.admissionsbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "registrations")
public class Registrations {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne
    @JoinColumn(name = "subject_set_id")
    private SubjectSet subjectSet;
    @OneToOne
    @JoinColumn(name = "class_code")
    private MajorClass majorClass;
}
