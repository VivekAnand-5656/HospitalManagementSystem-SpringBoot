package com.example.MyHospitalManagementSystem.dto;

import com.example.MyHospitalManagementSystem.entity.Appointment;
import com.example.MyHospitalManagementSystem.entity.Doctor;
import com.example.MyHospitalManagementSystem.entity.Patient;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentDTO {
    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;
    private Patient patient;
    private Doctor doctor;

//    ------------------
    public AppointmentDTO (Appointment appointment){
        this.id = appointment.getId();
        this.appointmentTime = appointment.getAppointmentTime();
        this.reason = appointment.getReason();
        this.patient = appointment.getPatient();
        this.doctor = appointment.getDoctor();
    }
}
