package com.otters.admissionsbackend.dto;

import com.otters.admissionsbackend.model.response.StudentResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentPass {
    private StudentResponse studentResponse;
    private Double totalPaperScore;

}
