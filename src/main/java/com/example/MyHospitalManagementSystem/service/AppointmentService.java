package com.example.MyHospitalManagementSystem.service;

import com.example.MyHospitalManagementSystem.dto.AppointmentRequestDTO;
import com.example.MyHospitalManagementSystem.dto.AppointmentsResponseDTO;
import com.example.MyHospitalManagementSystem.entity.Appointment;
import com.example.MyHospitalManagementSystem.entity.Doctor;
import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.enums.AppointmentStatus;
import com.example.MyHospitalManagementSystem.repository.AppointmentRepository;
import com.example.MyHospitalManagementSystem.repository.DoctorRepository;
import com.example.MyHospitalManagementSystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public AppointmentsResponseDTO createAppointment(AppointmentRequestDTO appointmentDTO,Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()->new EntityNotFoundException("Patient is not found"));
        Doctor doctor = doctorRepository.findFirstByDepartmentsContaining(appointmentDTO.getDepartment())
                .orElseThrow(()->new EntityNotFoundException("Doctor is not available"));

//        ------ Create Appointment ------
        Appointment appointment = new Appointment();

        appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setReason(appointmentDTO.getReason());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentStatus(AppointmentStatus.BOOKED);
        Appointment saveAppointment = appointmentRepository.save(appointment);

        AppointmentsResponseDTO appointmentsResponseDTO = new AppointmentsResponseDTO();
        appointmentsResponseDTO.setId(saveAppointment.getId());
        appointmentsResponseDTO.setAppointmentTime(saveAppointment.getAppointmentTime());
        appointmentsResponseDTO.setAppointmentDate(saveAppointment.getAppointmentDate());
        appointmentsResponseDTO.setReason(saveAppointment.getReason());

        appointmentsResponseDTO.setDoctorId(saveAppointment.getDoctor().getId());
        appointmentsResponseDTO.setPatientId(saveAppointment.getPatient().getId());

        appointmentsResponseDTO.setPatientName(saveAppointment.getPatient().getName());
        appointmentsResponseDTO.setDoctorName(saveAppointment.getDoctor().getName());

        return appointmentsResponseDTO;
    }

//    Deletion Pending

    @Transactional
    public void deleteAppointmentById(Long id){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Appoint not exists"));
        appointmentRepository.delete(appointment);
        System.out.println("Appointment Deleted Successfully");
    }
//    ------- Cancel Appointment -------
    @Transactional
    public void cancelAppointment(Long appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId).
                orElseThrow(()-> new RuntimeException("Appointment Not Found"));

        if(appointment.getAppointmentStatus()  == AppointmentStatus.CANCELLED){
            throw new RuntimeException("Appointment already cancelled");
        }
        appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
        Appointment appointmentSave =  appointmentRepository.save(appointment);
        System.out.println("Appointment Cancelled Successfully");
    }
//    ----- Appointment Status -----
    @Transactional
    public void appointmentStatus(Long appointmentId){
        Appointment appointment = appointmentRepository.findById(appointmentId).
                orElseThrow(()-> new RuntimeException("Appointment Not Found"));
        System.out.println("Appointment Cancelled Successfully"+ appointment.getAppointmentStatus() );
    }

//    --- Getting ---



}
