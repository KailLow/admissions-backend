package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.exceptionHandler.Error;
import com.otters.admissionsbackend.model.ExamRoom;
import com.otters.admissionsbackend.model.Subject;
import com.otters.admissionsbackend.model.paper.PaperContainers;
import com.otters.admissionsbackend.model.request.PaperContainerRequest;
import com.otters.admissionsbackend.repository.ExamRoomRepository;
import com.otters.admissionsbackend.repository.PaperContainerRepository;
import com.otters.admissionsbackend.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class PaperContainerService {
    private final PaperContainerRepository repository;
    private final SubjectRepository subjectRepository;
    private final ExamRoomRepository examRoomRepository;

    public PaperContainerService(PaperContainerRepository repository, SubjectRepository subjectRepository, ExamRoomRepository examRoomRepository) {
        this.repository = repository;
        this.subjectRepository = subjectRepository;
        this.examRoomRepository = examRoomRepository;
    }

    public PaperContainers add (PaperContainerRequest request) {
        PaperContainers paperContainers = new PaperContainers();
        paperContainers.setNumberOfPapers(request.getNumberOfPapers());

        Optional<Subject> subject = subjectRepository.findById(request.getSubjectId());
        if (subject.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Subject not found").toString()
            );
        paperContainers.setSubject(subject.get());
        List<ExamRoom> examRooms = new ArrayList<>();
        for (String id: request.getExamRoomId()
             ) {
            Optional<ExamRoom> examRoom = examRoomRepository.findById(id);
            if (examRoom.isEmpty())
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, new Error("Exam room not found").toString()
                );
            ExamRoom room = examRoom.get();
            examRooms.add(room);
        }

        return repository.save(paperContainers);
    }

    public Optional<PaperContainers> findById(String id) {
        return repository.findById(id);
    }

    public List<PaperContainers> findAll() {
        return repository.findAll();

    }
}
