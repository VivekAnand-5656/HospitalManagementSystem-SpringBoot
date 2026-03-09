package com.example.MyHospitalManagementSystem.service;

import com.example.MyHospitalManagementSystem.dto.PatientDTO;
import com.example.MyHospitalManagementSystem.dto.UpdatePatientRequestDTO;
import com.example.MyHospitalManagementSystem.dto.UpdatePatientResponseDTO;
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
//    ====== Update Patient Profile =======
    @Transactional
    public UpdatePatientResponseDTO updatePatientResponseDTO(UpdatePatientRequestDTO requestDTO){
        Patient patient = patientRepository.findById(requestDTO.getId()).orElseThrow(()-> new RuntimeException("Pateint Not Found"));
        patient.setName(requestDTO.getName());
        patient.setEmail(requestDTO.getEmail());
        patient.setGender(requestDTO.getGender());
        patient.setCreatedAt(requestDTO.getCreatedAt());
        patient.setBirthDate(requestDTO.getBirthDate());
        patient.setBloodGroup(requestDTO.getBloodGroup());

        patientRepository.save(patient);
        return new UpdatePatientResponseDTO(patient);

    }

}
