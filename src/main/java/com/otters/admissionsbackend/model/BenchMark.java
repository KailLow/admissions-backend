package com.otters.admissionsbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    @JsonIgnoreProperties(value = {"benchMarks"})
    private MajorClass majorClass;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonIgnoreProperties(value = {"benchMarks"})
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    @JsonIgnoreProperties(value = {"benchMarks"})
    private Exam exam;
}
