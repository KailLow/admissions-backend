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
public class ProfileDTO {
    private String numberId;
    private String fullName;
    private String gender;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String placeOfBirth;
    private String ethnicType;
    private String houseHold;
    private String address;
    private String school;
}
