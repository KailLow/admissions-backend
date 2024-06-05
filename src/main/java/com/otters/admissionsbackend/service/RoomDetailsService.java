package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.ExamRoom;
import com.otters.admissionsbackend.model.Student;
import com.otters.admissionsbackend.model.paper.PaperContainers;
import com.otters.admissionsbackend.repository.ExamRoomDetailsRepository;
import com.otters.admissionsbackend.repository.ExamRoomRepository;
import com.otters.admissionsbackend.repository.PaperContainerRepository;
import com.otters.admissionsbackend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomDetailsService {
    private final PaperContainerRepository paperContainerRepository;
    private final ExamRoomRepository examRoomRepository;
    private final ExamRoomDetailsRepository examRoomDetailsRepository;
    private final StudentRepository studentRepository;

    public RoomDetailsService(PaperContainerRepository paperContainerRepository, ExamRoomRepository examRoomRepository, ExamRoomDetailsRepository examRoomDetailsRepository, StudentRepository studentRepository) {
        this.paperContainerRepository = paperContainerRepository;
        this.examRoomRepository = examRoomRepository;
        this.examRoomDetailsRepository = examRoomDetailsRepository;
        this.studentRepository = studentRepository;
    }

    public List<Student> get(String paperContainerId) {
        Optional<PaperContainers> paperContainers = paperContainerRepository.findById(paperContainerId);
        Optional<ExamRoom> examRoom = examRoomRepository.findById(paperContainers.get().getExamRooms().getId());
        List<Student> students = examRoomDetailsRepository.findStudentsByExamRoomId(examRoom.get().getRoom().getId());

        return students;
    }
}
