package com.otters.admissionsbackend.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaperRequest {
    private Double score;
    private Integer numberOfPapers;
    private String studentId;
    private String subjectId;
}
