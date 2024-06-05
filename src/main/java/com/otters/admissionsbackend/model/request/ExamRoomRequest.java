package com.otters.admissionsbackend.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExamRoomRequest {
    private String examRoomId;
    private String subjectId;
    private Date date;
}
