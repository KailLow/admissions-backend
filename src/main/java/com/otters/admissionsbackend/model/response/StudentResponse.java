package com.otters.admissionsbackend.model.response;

import com.otters.admissionsbackend.model.Registrations;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private String id;
    private String profileId;
    private String fullName;
    private String gender;
    private Registrations passed;
}
