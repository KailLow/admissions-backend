package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.dto.BenchMarkDTO;
import com.otters.admissionsbackend.model.*;
import com.otters.admissionsbackend.exceptionHandler.Error;
import com.otters.admissionsbackend.repository.BenchMarkRepository;
import com.otters.admissionsbackend.repository.ClassRepository;
import com.otters.admissionsbackend.repository.ExamRepository;
import com.otters.admissionsbackend.repository.SubjectRepository;
import com.otters.admissionsbackend.utils.ICheckCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BenchMarkService implements ICheckCommand {
    private final BenchMarkRepository repository;
    private final ExamRepository examRepository;
    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;

    public BenchMark add(BenchMarkDTO request){
        Optional<Exam> examOptional = examRepository.findById(request.getExamId());
        if(examOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Exam not existed").toString());
        }
        Optional<Subject> subjectOptional = subjectRepository.findById(request.getSubjectId());
        if(subjectOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Subject not existed").toString());
        }
        Optional<MajorClass> classOptional = classRepository.findById(request.getMajorClassId());
        if(classOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Class not existed").toString());
        }
        BenchMark benchMark = new BenchMark();
        benchMark.setExam(examOptional.get());
        benchMark.setSubject(subjectOptional.get());
        benchMark.setMajorClass(classOptional.get());
        benchMark.setScore(request.getScore());
        return repository.save(benchMark);
    }

    public List<BenchMark> findAll() {
        return repository.findAll();
    }

    public BenchMark findById(String id) {
        Optional<BenchMark> benchMark = repository.findById(id);
        if (!checkExisted(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Benchmark not found");
        }
        return benchMark.get();
    }

    public void deleteById(String id) {
        if (!checkExisted(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Benchmark not found");
        }
        repository.deleteById(id);
    }

    public BenchMark update(String id, BenchMarkDTO request) {
        BenchMark existingBenchMark = findById(id);

        Optional<Exam> examOptional = examRepository.findById(request.getExamId());
        Optional<Subject> subjectOptional = subjectRepository.findById(request.getSubjectId());
        Optional<MajorClass> classOptional = classRepository.findById(request.getMajorClassId());

        if (examOptional.isEmpty() || subjectOptional.isEmpty() || classOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ID(s) provided");
        }

        existingBenchMark.setExam(examOptional.get());
        existingBenchMark.setSubject(subjectOptional.get());
        existingBenchMark.setMajorClass(classOptional.get());
        existingBenchMark.setScore(request.getScore());
        return repository.save(existingBenchMark);
    }

    @Override
    public boolean checkExisted(String id) {
        return repository.existsById(id);
    }
}
