package com.otters.admissionsbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.otters.admissionsbackend.model.paper.Paper;
import com.otters.admissionsbackend.model.paper.PaperContainers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "subject")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Integer parameter;
    //minutes
    private Integer time;

    @JsonIgnoreProperties(value = {"benchMarks"})
    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<BenchMark> benchMarks;

    @JsonIgnoreProperties(value = {"examRooms"})
    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<ExamRoom> examRooms;


    @JsonIgnoreProperties(value = {"subjectSets"})
    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private List<SubjectSet> subjectSets;


    @JsonIgnoreProperties(value = {"papers"})
    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<Paper> papers;

    @JsonIgnoreProperties(value = {"paperContainers"})
    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<PaperContainers> paperContainers;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParameter(Integer parameter) {
        this.parameter = parameter;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

}
