package com.otters.admissionsbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bench_mark")
@Getter
@Setter
public class BenchMark {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Double score;
    @OneToOne
    @JoinColumn(name = "class_id")
    private MajorClass majorClass;
    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @OneToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
}
