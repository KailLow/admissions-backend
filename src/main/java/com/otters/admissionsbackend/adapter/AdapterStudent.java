package com.otters.admissionsbackend.adapter;

import com.otters.admissionsbackend.dto.StudentPass;
import com.otters.admissionsbackend.model.Student;
import com.otters.admissionsbackend.model.response.StudentResponse;
import com.otters.admissionsbackend.repository.StudentRepository;
import com.otters.admissionsbackend.utils.ClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class AdapterStudent implements ClientInterface {

    @Override
    public void changeData(String id, Double score) {
        
    }
}
