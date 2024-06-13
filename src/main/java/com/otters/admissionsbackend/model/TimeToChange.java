package com.otters.admissionsbackend.model;

import com.otters.admissionsbackend.utils.Prototype;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "time_to_change")
@AllArgsConstructor
@NoArgsConstructor
public class TimeToChange {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date wishChangeTime;
    private Date registrationTime;
    private Date admissionTime;
    private Date feePayTime;
    private Date personalInformationChangeTime;
    private Date resultTime;
    @OneToOne
    @JoinColumn(name= "exam_id")
    private Exam exam;

//    @Override
//    public Prototype clone() {
//        return new TimeToChange(this.id,this.wishChangeTime, this.registrationTime, this.admissionTime, this.feePayTime, this.personalInformationChangeTime, this.resultTime, null);
//    }
}
