package com.otters.admissionsbackend.dto;

import lombok.Data;

@Data
public class StudentPaperScoreDTO {
    private String studentId;
    private Double totalPaperScore;
}
