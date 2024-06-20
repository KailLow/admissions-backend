package com.otters.admissionsbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String year;

    @JsonIgnoreProperties(value = {"benchMarks"})
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "exam")
    private List<BenchMark> benchMarks;
}
