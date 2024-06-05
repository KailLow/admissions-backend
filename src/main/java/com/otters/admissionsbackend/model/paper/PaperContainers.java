package com.otters.admissionsbackend.model.paper;

import com.otters.admissionsbackend.model.ExamRoom;
import com.otters.admissionsbackend.model.Subject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "paper_container")
@Getter
@Setter
public class PaperContainers {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Integer numberOfPapers;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "exam_room_id")
    private ExamRoom examRooms;
}
