package com.otters.admissionsbackend.model;

import com.otters.admissionsbackend.utils.Prototype;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Getter
@Entity
@Table(name = "profile")
@RequiredArgsConstructor
@AllArgsConstructor
public class Profile implements Prototype {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fullName;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String placeOfBirth;
    private String ethnicType;
    private String houseHold;
    private String address;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public void setEthnicType(String ethnicType) {
        this.ethnicType = ethnicType;
    }

    public void setHouseHold(String houseHold) {
        this.houseHold = houseHold;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public Prototype clone() {
        return new Profile(
                this.id,
                this.fullName,
                this.dateOfBirth,
                this.phoneNumber,
                this.email,
                this.placeOfBirth,
                this.ethnicType,
                this.houseHold,
                this.placeOfBirth
        );
    }
}
