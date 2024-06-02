package com.otters.admissionsbackend.model.request;

import com.otters.admissionsbackend.model.Student;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExRoomDtlsRequest {
    private String studentId;
    private String examRoomId;
}
