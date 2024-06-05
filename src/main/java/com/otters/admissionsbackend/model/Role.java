package com.otters.admissionsbackend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("USER"),
    ADMIN("ADMIN"),
    STUDENT("STUDENT"),
    KHAOTHI("KHAOTHI"),
    DAOTAO("DAOTAO");

    private final String role;
}
