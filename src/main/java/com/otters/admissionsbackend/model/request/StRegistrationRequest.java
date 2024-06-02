package com.otters.admissionsbackend.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StRegistrationRequest {
    private String studentId;
    private String registrationId;
    private Integer numericalOrder;
}
