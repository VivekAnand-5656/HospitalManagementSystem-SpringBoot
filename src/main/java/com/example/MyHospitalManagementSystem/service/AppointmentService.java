package com.example.MyHospitalManagementSystem.service;

import com.example.MyHospitalManagementSystem.dto.AppointmentDTO;
import com.example.MyHospitalManagementSystem.entity.Appointment;
import com.example.MyHospitalManagementSystem.entity.Doctor;
import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.repository.AppointmentRepository;
import com.example.MyHospitalManagementSystem.repository.DoctorRepository;
import com.example.MyHospitalManagementSystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.MapsId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

//    -------------- Creation -----------
    @Transactional
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO){
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId()).orElseThrow(()->new EntityNotFoundException("Patient is not found"));
        Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctorId()).orElseThrow(()->new EntityNotFoundException("Doctor is not available"));

        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        appointment.setReason(appointmentDTO.getReason());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        Appointment saveAppointment = appointmentRepository.save(appointment);

        AppointmentDTO responseDto = new AppointmentDTO();
        responseDto.setId(saveAppointment.getId());
        responseDto.setAppointmentTime(saveAppointment.getAppointmentTime());
        responseDto.setReason(saveAppointment.getReason());
        responseDto.setPatientId(saveAppointment.getPatient().getId());
        responseDto.setDoctorId(saveAppointment.getDoctor().getId());

        return responseDto;
    }

//    Deletion Pending

    @Transactional
    public void deleteAppointmentById(Long id){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Appoint not exists"));
        appointmentRepository.delete(appointment);
        System.out.println("Appointment Deleted Successfully");

    }

//    --- Getting ---


}
