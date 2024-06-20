package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.paper.Paper;
import com.otters.admissionsbackend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassRegistrationService {
    private final StudentRepository studentRepository;
    private final RegistrationsRepository registrationsRepository;
    private final ClassRepository classRepository;
    private final StRegistrationDetailsRepository stRegistrationDetailsRepository;
    private final PaperRepository paperRepository;

    public void getStudent(String classId){
        List<Paper> paperOptional = paperRepository.findByOrderByScoreDesc();

    }
}
