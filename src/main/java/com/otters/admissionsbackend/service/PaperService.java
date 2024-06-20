package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.command.PaperCommand;
import com.otters.admissionsbackend.dto.StudentPaperScoreDTO;
import com.otters.admissionsbackend.exceptionHandler.Error;
import com.otters.admissionsbackend.model.Student;
import com.otters.admissionsbackend.model.Subject;
import com.otters.admissionsbackend.model.paper.Paper;
import com.otters.admissionsbackend.model.request.PaperRequest;
import com.otters.admissionsbackend.model.response.PaperResponse;
import com.otters.admissionsbackend.repository.PaperRepository;
import com.otters.admissionsbackend.repository.StudentRepository;
import com.otters.admissionsbackend.repository.SubjectRepository;
import com.otters.admissionsbackend.utils.ICheckCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaperService implements ICheckCommand {
    private final PaperRepository repository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    private static final PaperCommand command = new PaperCommand();

    public PaperService(PaperRepository repository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public Paper findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, new Error("Paper not found").toString()));
    }

    public List<PaperResponse> findAll() {
        List<Paper> papers = repository.findAll();
        List<PaperResponse> paperResponses = new ArrayList<>();
        for (Paper paper: papers
             ) {
            PaperResponse response = new PaperResponse();
            response.setId(paper.getId());
            response.setSubject(paper.getSubject());
            response.setScore(paper.getScore());
            response.setNumberOfPapers(paper.getNumberOfPapers());
            response.setStudentResponse(paper.getStudent());

            paperResponses.add(response);
        }

        return paperResponses;
    }

    public Paper add(PaperRequest paper) {
        Optional<Student> student = studentRepository.findById(paper.getStudentId());
        if (student.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Students not existed").toString());
        }

        Optional<Subject> subject = subjectRepository.findById(paper.getSubjectId());
        if (subject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Subject not existed").toString());
        }

        Paper newPaper = new Paper();
        command.fetch(newPaper, paper);
        newPaper.setStudent(student.get());
        newPaper.setSubject(subject.get());
        return repository.save(newPaper);
    }

    public void delete(String id) throws ResponseStatusException {
        if (checkExisted(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Paper not existed").toString());
        }
        repository.deleteById(id);
    }

    public Paper update(String id, PaperRequest request) {
        Optional<Paper> paperOptional = repository.findById(id);
        if (paperOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Paper not existed").toString());
        }
        Paper paper = paperOptional.get();
        command.fetch(paper, request);
        return repository.save(paper);
    }


    public Page<Paper> getAllPapersSortedByScore(Pageable pageable) {
        return repository.findByOrderByScoreDesc(pageable);
    }

    public List<StudentPaperScoreDTO> getTopStudentsByTotalPaperScore(int limit) {
        return repository.findTopStudentsByTotalPaperScore(limit);
    }

    @Override
    public boolean checkExisted(String id) {
        Optional<Paper> paper = repository.findById(id);
        if (paper.isEmpty()) {
            return false;
        }
        return true;
    }
}
