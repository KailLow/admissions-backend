package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.dto.ExamRoomDetailDTO;
import com.otters.admissionsbackend.exceptionHandler.Error;
import com.otters.admissionsbackend.model.ExamRoom;
import com.otters.admissionsbackend.model.ExamRoomDetails;
import com.otters.admissionsbackend.model.Student;
import com.otters.admissionsbackend.repository.ExamRoomDetailsRepository;
import com.otters.admissionsbackend.repository.ExamRoomRepository;
import com.otters.admissionsbackend.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ExamRoomDetailsService {
    private final ExamRoomDetailsRepository repository;
    private final StudentRepository studentRepository;
    private final ExamRoomRepository examRoomRepository;

    public ExamRoomDetails add(ExamRoomDetailDTO request) {
        Optional<Student> student = studentRepository.findById(request.getStudentId());
        if (student.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Student not existed").toString()
            );
        Optional<ExamRoom> examRoom = examRoomRepository.findById(request.getExamRoomId());
        if (examRoom.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Exam room not existed").toString()
            );

        ExamRoomDetails details = new ExamRoomDetails();
        details.setExamRoom(examRoom.get());
        details.setStudent(student.get());
        return repository.save(details);
    }

    public List<ExamRoomDetails> findAll() {
        return repository.findAll();
    }

    public ExamRoomDetails findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ExamRoomDetails not found"));
    }

    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ExamRoomDetails not found");
        }
        repository.deleteById(id);
    }
}
