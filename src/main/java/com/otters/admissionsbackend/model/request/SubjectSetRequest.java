package com.otters.admissionsbackend.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectSetRequest {
    private String name;
    private List<String> subjects;
}
