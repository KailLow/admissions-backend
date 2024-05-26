package com.otters.admissionsbackend.model;

import com.otters.admissionsbackend.utils.Prototype;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Subject implements Prototype {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Integer parameter;
    //minutes
    private Integer time;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParameter(Integer parameter) {
        this.parameter = parameter;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public Prototype clone() {
        return new Subject(
                this.id,
                this.name,
                this.parameter,
                this.time
        );
    }
}
