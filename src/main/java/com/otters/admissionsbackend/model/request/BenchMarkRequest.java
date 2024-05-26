package com.otters.admissionsbackend.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BenchMarkRequest {
    private Double score;
    private String majorClassId;
    private String subjectId;
    private String examId;
}
