package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.BenchMark;
import com.otters.admissionsbackend.model.Exam;
import com.otters.admissionsbackend.model.MajorClass;
import com.otters.admissionsbackend.model.Subject;
import com.otters.admissionsbackend.model.request.BenchMarkRequest;
import com.otters.admissionsbackend.repository.BenchMarkRepository;
import com.otters.admissionsbackend.repository.ClassRepository;
import com.otters.admissionsbackend.repository.ExamRepository;
import com.otters.admissionsbackend.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class BenchMarkService {
    private final BenchMarkRepository repository;
    private final ExamRepository examRepository;
    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;

    public BenchMarkService(BenchMarkRepository repository, ExamRepository examRepository, ClassRepository classRepository, SubjectRepository subjectRepository) {
        this.repository = repository;
        this.examRepository = examRepository;
        this.classRepository = classRepository;
        this.subjectRepository = subjectRepository;
    }

    public BenchMark add(BenchMarkRequest request){
        Optional<Exam> examOptional = examRepository.findById(request.getExamId());
        Optional<Subject> subjectOptional = subjectRepository.findById(request.getSubjectId());
        Optional<MajorClass> classOptional = classRepository.findById(request.getMajorClassId());
        BenchMark benchMark = new BenchMark();
        benchMark.setExam(examOptional.get());
        benchMark.setSubject(subjectOptional.get());
        benchMark.setMajorClass(classOptional.get());
        benchMark.setScore(request.getScore());
        return repository.save(benchMark);
    }
}
