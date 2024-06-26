package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.exceptionHandler.Error;
import com.otters.admissionsbackend.model.ExamRoom;
import com.otters.admissionsbackend.model.Subject;
import com.otters.admissionsbackend.model.paper.PaperContainers;
import com.otters.admissionsbackend.model.request.PaperContainerRequest;
import com.otters.admissionsbackend.repository.ExamRoomRepository;
import com.otters.admissionsbackend.repository.PaperContainerRepository;
import com.otters.admissionsbackend.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PaperContainerService {
    private final PaperContainerRepository repository;
    private final SubjectRepository subjectRepository;
    private final ExamRoomRepository examRoomRepository;

    public PaperContainers add(PaperContainerRequest request) {
        PaperContainers paperContainers = new PaperContainers();
        paperContainers.setNumberOfPapers(request.getNumberOfPapers());

        Optional<Subject> subject = subjectRepository.findById(request.getSubjectId());
        if (subject.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Subject not found").toString()
            );
        paperContainers.setSubject(subject.get());

            Optional<ExamRoom> examRoom = examRoomRepository.findById(request.getExamRoomId());
            if (examRoom.isEmpty())
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, new Error("Exam room not found").toString()
                );
            ExamRoom room = examRoom.get();
            paperContainers.setExamRooms(room);

        return repository.save(paperContainers);
    }

    public Optional<PaperContainers> findById(String id) {
        return repository.findById(id);
    }

    public List<PaperContainers> findAll() {
        return repository.findAll();
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Paper container not found").toString());
        }
        repository.deleteById(id);
    }

    public PaperContainers update(String id, PaperContainerRequest request) {
        Optional<PaperContainers> paperContainersOptional = repository.findById(id);
        if (paperContainersOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Paper container not found").toString());
        }
        PaperContainers paperContainers = paperContainersOptional.get();
        paperContainers.setNumberOfPapers(request.getNumberOfPapers());

        Optional<Subject> subject = subjectRepository.findById(request.getSubjectId());
        if (subject.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Subject not found").toString()
            );
        paperContainers.setSubject(subject.get());

            Optional<ExamRoom> examRoom = examRoomRepository.findById(request.getExamRoomId());
            if (examRoom.isEmpty())
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, new Error("Exam room not found").toString()
                );
        paperContainers.setExamRooms(examRoom.get());

        return repository.save(paperContainers);
    }
}
