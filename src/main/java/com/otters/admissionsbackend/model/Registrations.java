package com.otters.admissionsbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
