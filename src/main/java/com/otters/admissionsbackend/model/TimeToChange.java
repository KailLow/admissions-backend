package com.otters.admissionsbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "time_to_change")
public class TimeToChange {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date registrationTime;
    private Date admissionTime;
    private Date feePayTime;
    private Date personalInformationChangeTime;
    private Date ResultTime;
    @OneToOne
    @JoinColumn(name= "exam_id")
    private Exam exam;
}
