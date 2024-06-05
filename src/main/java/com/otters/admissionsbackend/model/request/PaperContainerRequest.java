package com.otters.admissionsbackend.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PaperContainerRequest {
    private Integer numberOfPapers;
    private String subjectId;
    private Set<String> examRoomId;
}
