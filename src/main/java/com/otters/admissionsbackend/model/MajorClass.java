package com.otters.admissionsbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "major_class")
@Getter
@Setter
public class MajorClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String year;
    private Integer quotas;

    @JsonIgnoreProperties(value = {"benchMarks"})
    @JsonIgnore
    @OneToMany(mappedBy = "majorClass")
    private List<BenchMark> benchMarks;

    @JsonIgnoreProperties(value = {"registrations"})
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "majorClass")
    private List<Registrations> registrations;
}
