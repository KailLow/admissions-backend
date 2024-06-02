package com.otters.admissionsbackend.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private String subjectSetId;
    private String classId;
}
