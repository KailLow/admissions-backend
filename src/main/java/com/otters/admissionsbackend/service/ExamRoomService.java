package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.exceptionHandler.Error;
import com.otters.admissionsbackend.model.ExamRoom;
import com.otters.admissionsbackend.model.Room;
import com.otters.admissionsbackend.model.Subject;
import com.otters.admissionsbackend.model.request.ExamRoomRequest;
import com.otters.admissionsbackend.repository.ExamRoomRepository;
import com.otters.admissionsbackend.repository.RoomRepository;
import com.otters.admissionsbackend.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ExamRoomService {
    private final ExamRoomRepository repository;
    private final RoomRepository roomRepository;
    private final SubjectRepository subjectRepository;

    public ExamRoomService(ExamRoomRepository repository, RoomRepository roomRepository, SubjectRepository subjectRepository) {
        this.repository = repository;
        this.roomRepository = roomRepository;
        this.subjectRepository = subjectRepository;
    }

    public ExamRoom add(ExamRoomRequest request) {
        Optional<Room> room = roomRepository.findById(request.getExamRoomId());
        if (room.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Room not existed").toString()
            );

        Optional<Subject> subject = subjectRepository.findById(request.getSubjectId());
        if (subject.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Subject not existed").toString()
            );

        ExamRoom examRoom = new ExamRoom();
        examRoom.setPaperContainers(null);
        examRoom.setRoom(room.get());
        examRoom.setSubject(subject.get());
        examRoom.setDate(request.getDate());
        return repository.save(examRoom);
    }

    public ExamRoom findById(String id) {
        Optional<ExamRoom> examRoom = repository.findById(id);
        if (examRoom.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Exam room not existed").toString()
            );
        return examRoom.get();
    }

    public List<ExamRoom> findAll() {
        return repository.findAll();
    }

    public void remove(String id) {
        Optional<ExamRoom> examRoom = repository.findById(id);
        if (examRoom.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Exam room not existed").toString()
            );
        repository.deleteById(id);
    }

    public ExamRoom update(String id, ExamRoomRequest request) {
        Optional<ExamRoom> examRoomOptional = repository.findById(id);
        if (examRoomOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Exam room not existed").toString()
            );

        Optional<Room> room = roomRepository.findById(request.getExamRoomId());
        if (room.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Room not existed").toString()
            );

        Optional<Subject> subject = subjectRepository.findById(request.getSubjectId());
        if (subject.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Subject not existed").toString()
            );

        ExamRoom examRoom = examRoomOptional.get();
        examRoom.setRoom(room.get());
        examRoom.setSubject(subject.get());
        examRoom.setDate(request.getDate());
        return repository.save(examRoom);
    }
}
