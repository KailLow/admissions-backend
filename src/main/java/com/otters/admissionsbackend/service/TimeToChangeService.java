package com.otters.admissionsbackend.service;

import com.otters.admissionsbackend.model.TimeToChange;
import com.otters.admissionsbackend.repository.TimeToChangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeToChangeService {
    private final TimeToChangeRepository repository;

    public TimeToChange add(TimeToChange timeToChange) {
        TimeToChange time = new TimeToChange();
        time.setAdmissionTime(time.getAdmissionTime());
        time.setWishChangeTime(timeToChange.getWishChangeTime());
        time.setRegistrationTime(time.getRegistrationTime());
        time.setResultTime(time.getResultTime());
        time.setFeePayTime(time.getFeePayTime());
        time.setExam(timeToChange.getExam());
        time.setPersonalInformationChangeTime(timeToChange.getPersonalInformationChangeTime());
        return repository.save(time);
    }

    public List<TimeToChange> findAll() {
        return repository.findAll();
    }

    public TimeToChange findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TimeToChange not found"));
    }

    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TimeToChange not found");
        }
        repository.deleteById(id);
    }
}
