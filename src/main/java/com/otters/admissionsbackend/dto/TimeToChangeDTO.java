package com.otters.admissionsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeToChangeDTO {
    private Date wishChangeTime;
    private Date registrationTime;
    private Date admissionTime;
    private Date feePayTime;
    private Date personalInformationChangeTime;
    private Date resultTime;
    private String examId;
}
