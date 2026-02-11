package com.example.MyHospitalManagementSystem.service;


import com.example.MyHospitalManagementSystem.entity.Insurance;
import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.repository.InsuranceRepository;
import com.example.MyHospitalManagementSystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new EntityNotFoundException("Patient no found with this id"));

        patient.setInsurance(insurance);
        insurance.setPatient(patient);
        insuranceRepository.save(insurance);

        return patient;
    }
}
