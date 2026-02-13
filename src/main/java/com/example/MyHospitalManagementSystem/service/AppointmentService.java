package com.example.MyHospitalManagementSystem.service;

import com.example.MyHospitalManagementSystem.dto.AppointmentDTO;
import com.example.MyHospitalManagementSystem.entity.Appointment;
import com.example.MyHospitalManagementSystem.entity.Doctor;
import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.repository.AppointmentRepository;
import com.example.MyHospitalManagementSystem.repository.DoctorRepository;
import com.example.MyHospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public List<AppointmentDTO> createNewAppoinment(List<Appointment> appointments, Long doctorId, Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        for(Appointment appointment : appointments){
            if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have id");
            appointment.setPatient(patient);
            appointment.setDoctor(doctor);
            patient.getAppointments().add(appointment);
        }
        List<Appointment> savedAppointmens = appointmentRepository.saveAll(appointments);
        List<AppointmentDTO> appointmentDTOS =  new ArrayList<>();
        for(Appointment appointment : savedAppointmens){
            appointmentDTOS.add(new AppointmentDTO(appointment));
        }
        return appointmentDTOS;

    }

//    Deletion Pending
}
