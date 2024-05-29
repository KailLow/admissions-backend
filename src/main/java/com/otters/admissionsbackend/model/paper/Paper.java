package com.otters.admissionsbackend.model.paper;

import com.otters.admissionsbackend.model.Student;
import com.otters.admissionsbackend.model.Subject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "paper")
@Getter
@Setter
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Double score;
    private Integer numberOfPapers;

    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
