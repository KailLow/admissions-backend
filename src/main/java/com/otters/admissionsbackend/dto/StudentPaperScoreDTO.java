package com.otters.admissionsbackend.dto;

import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentPaperScoreDTO {
    private String studentId;
    private Double totalPaperScore;
}
