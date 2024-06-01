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
    @ManyToOne
    @JoinColumn(name = "class_id")
    private MajorClass majorClass;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
}
