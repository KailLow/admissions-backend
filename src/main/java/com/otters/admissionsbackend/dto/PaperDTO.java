package com.otters.admissionsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaperDTO {
    private Double score;
    private Integer numberOfPapers;
    private String studentId;
    private String subjectId;
}
