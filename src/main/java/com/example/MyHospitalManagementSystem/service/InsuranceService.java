package com.example.MyHospitalManagementSystem.service;


import com.example.MyHospitalManagementSystem.dto.InsuranceDTO;
import com.example.MyHospitalManagementSystem.dto.PatientDTO;
import com.example.MyHospitalManagementSystem.entity.Insurance;
import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.repository.InsuranceRepository;
import com.example.MyHospitalManagementSystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public InsuranceDTO assignInsuranceToPatient(InsuranceDTO insuranceDTO){
        if(insuranceDTO.getPatientId() == null){
            throw new IllegalArgumentException("Patient id must not be null");
        }
        Patient patient = patientRepository.findById(insuranceDTO.getPatientId()).orElseThrow(()-> new EntityNotFoundException("Patient no found with this id"));

        Insurance insurance = new Insurance();
        insurance.setProvider(insuranceDTO.getProvider());
        insurance.setCreatedAt(insuranceDTO.getCreatedAt());
        insurance.setValidUntil(insuranceDTO.getValidUntil());
        insurance.setPolicyNumber(insuranceDTO.getPolicyNumber());
        insurance.setPatient(patient);
        patient.setInsurance(insurance);

        Insurance saveInsurance = insuranceRepository.save(insurance);

        InsuranceDTO responseDto = new InsuranceDTO();
        responseDto.setProvider(saveInsurance.getProvider());
        responseDto.setCreatedAt(LocalDateTime.now());
        responseDto.setValidUntil(saveInsurance.getValidUntil());
        responseDto.setPolicyNumber(saveInsurance.getPolicyNumber());
        responseDto.setPatient(saveInsurance.getPatient());
        return responseDto;
    }

}
