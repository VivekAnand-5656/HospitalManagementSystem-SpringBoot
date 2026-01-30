package com.example.MyHospitalManagementSystem.service;

import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    @Transactional
    public Patient getPatientById(Long id){
        Patient p1 = patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Patient not found"));
        p1.setName("Rahul Roy");

        return p1;
    }

}
