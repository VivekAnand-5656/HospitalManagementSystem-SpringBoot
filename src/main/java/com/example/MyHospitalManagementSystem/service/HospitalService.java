package com.example.MyHospitalManagementSystem.service;

import com.example.MyHospitalManagementSystem.dto.DoctorDTO;
import com.example.MyHospitalManagementSystem.entity.Doctor;
import com.example.MyHospitalManagementSystem.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final DoctorRepository doctorRepository;

    @Transactional
    public List<DoctorDTO> allDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOS = new ArrayList<>();

        for(Doctor doctor : doctors){
            doctorDTOS.add(new DoctorDTO(doctor));
        }
        return doctorDTOS;
    }
}
