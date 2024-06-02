package com.otters.admissionsbackend.command;

import com.otters.admissionsbackend.model.Profile;
import com.otters.admissionsbackend.utils.ICopyCommand;

public class ProfileCommand<T extends Profile> implements ICopyCommand<Profile> {
    @Override
    public void copy(Profile copy, Profile original) {
        copy.setFullName(original.getFullName());
        copy.setAddress(original.getAddress());
        copy.setEmail(original.getEmail());
        copy.setEthnicType(original.getEthnicType());
        copy.setDateOfBirth(original.getDateOfBirth());
        copy.setHouseHold(original.getHouseHold());
        copy.setPhoneNumber(original.getPhoneNumber());
        copy.setPlaceOfBirth(original.getPlaceOfBirth());
        copy.setNumberId(original.getNumberId());
        copy.setGender(original.getGender());
        copy.setSchool(original.getSchool());
    }
}
