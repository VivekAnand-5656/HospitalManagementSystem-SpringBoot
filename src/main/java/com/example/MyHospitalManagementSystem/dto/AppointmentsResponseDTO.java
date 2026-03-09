package com.example.MyHospitalManagementSystem.dto;

import com.example.MyHospitalManagementSystem.entity.Appointment;
import com.example.MyHospitalManagementSystem.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentsResponseDTO {
    private Long id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String reason;

    private Long patientId;
    private String patientName;

    private Long doctorId;
    private String doctorName;

    private AppointmentStatus appointmentStatus;
    public AppointmentsResponseDTO(Appointment appointment){
        this.id = appointment.getId();
        this.appointmentDate = appointment.getAppointmentDate();
        this.appointmentTime = appointment.getAppointmentTime();
        this.reason = appointment.getReason();
        this.appointmentStatus = appointment.getAppointmentStatus();

        if(appointment.getPatient() != null){
            this.patientId = appointment.getPatient().getId();
            this.patientName = appointment.getPatient().getName();
        }

        if(appointment.getDoctor() != null){
            this.doctorId = appointment.getDoctor().getId();
            this.doctorName = appointment.getDoctor().getName();
        }
    }
}
