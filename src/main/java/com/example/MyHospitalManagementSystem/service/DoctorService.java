package com.example.MyHospitalManagementSystem.service;

import com.example.MyHospitalManagementSystem.dto.AppointmentDTO;
import com.example.MyHospitalManagementSystem.dto.DoctorDTO;
import com.example.MyHospitalManagementSystem.entity.Appointment;
import com.example.MyHospitalManagementSystem.entity.Doctor;
import com.example.MyHospitalManagementSystem.repository.AppointmentRepository;
import com.example.MyHospitalManagementSystem.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
//    ----- View Own Appointments ---------
//    @Transactional
//    public List<AppointmentDTO> getMyAppointments(Long id){
//        List<Appointment> appointments = appointmentRepository.findAll();
//    }

    @Transactional
    public List<DoctorDTO> doctorList(){
        List<Doctor> doctor = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for(Doctor doctor1 : doctor){
            doctorDTOS.add(new DoctorDTO(doctor1));
        }
        return doctorDTOS;
    }

    @Transactional
    public DoctorDTO getByIdDoctor(Long id){
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Doctor in not available"));
        return new  DoctorDTO(doctor);
    }

//    @Transactional
//    public DoctorDTO getDoctorByName(String name){
//        Doctor doctor = doctorRepository.getDoctorByName(name);
//        return new DoctorDTO(doctor);
//    }
//    Pending Doctor CRUD
}
