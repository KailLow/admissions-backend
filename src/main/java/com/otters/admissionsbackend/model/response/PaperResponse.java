package com.otters.admissionsbackend.model.response;

import com.otters.admissionsbackend.model.Student;
import com.otters.admissionsbackend.model.Subject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaperResponse {
    private String id;
    private Double score;
    private Integer numberOfPapers;
    private StudentResponse student;
    private Subject subject;

    public void setStudentResponse(Student response) {
        student = new StudentResponse();
        student.setId(response.getId());
        student.setGender(response.getProfile().getGender());
        student.setPassed(response.getPassed());
        student.setFullName(response.getProfile().getFullName());
        student.setProfileId(response.getProfile().getId());
    }
}
