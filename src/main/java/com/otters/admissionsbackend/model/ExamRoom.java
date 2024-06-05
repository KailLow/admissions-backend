package com.otters.admissionsbackend.model;

import com.otters.admissionsbackend.model.paper.PaperContainers;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "exam_room")
@Getter
@Setter
public class ExamRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private Date date;
}
