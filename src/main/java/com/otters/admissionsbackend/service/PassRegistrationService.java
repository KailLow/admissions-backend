package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.paper.Paper;
import com.otters.admissionsbackend.repository.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class PassRegistrationService {
    private final StudentRepository studentRepository;
    private final RegistrationsRepository registrationsRepository;
    private final ClassRepository classRepository;
    private final StRegistrationDetailsRepository stRegistrationDetailsRepository;
    private final PaperRepository paperRepository;

    public PassRegistrationService(StudentRepository studentRepository, RegistrationsRepository registrationsRepository, ClassRepository classRepository, StRegistrationDetailsRepository stRegistrationDetailsRepository, PaperRepository paperRepository) {
        this.studentRepository = studentRepository;
        this.registrationsRepository = registrationsRepository;
        this.classRepository = classRepository;
        this.stRegistrationDetailsRepository = stRegistrationDetailsRepository;
        this.paperRepository = paperRepository;
    }

    public void getStudent(String classId){
        List<Paper> paperOptional = paperRepository.findByOrderByScoreDesc();

    }
}
