package com.otters.admissionsbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_room_detail")
public class ExamRoomDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_room_id")
    private ExamRoom examRoom;
}
