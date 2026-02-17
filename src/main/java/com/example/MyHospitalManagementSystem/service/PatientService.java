package com.example.MyHospitalManagementSystem.service;

import com.example.MyHospitalManagementSystem.dto.PatientDTO;
import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;


//    -------- Create Patient ------
    public PatientDTO createPatient(PatientDTO dto){
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setBirthDate(dto.getBirthDate());
        patient.setEmail(dto.getEmail());
        patient.setGender(dto.getGender());
        patient.setBloodGroup(dto.getBloodGroup());
        patient.setCreatedAt(LocalDateTime.now());


        Patient savePatient = patientRepository.save(patient);
        return new PatientDTO(savePatient);
    }

//    ----- Updations -----
//    ---- Name Update By search Name ---
    @Transactional
    public PatientDTO updatePatientByName(String name,String updateName){
        Patient patient = patientRepository.findPatientByName(name);
        if(patient!=null){
            patient.setName(updateName);
        } else {
            throw new RuntimeException("Patient Not Found");
        }

        return new PatientDTO(patient);
    }

//    ------ Delete By Id -----
    @Transactional
    public PatientDTO findById(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Patient not found"));


        return new PatientDTO(patient);
    }

}
