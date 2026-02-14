package com.example.MyHospitalManagementSystem.dto;

import com.example.MyHospitalManagementSystem.entity.Appointment;
import com.example.MyHospitalManagementSystem.entity.Doctor;
import com.example.MyHospitalManagementSystem.entity.Patient;
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
    private Long patientId;

    private Long doctorId;

//    ------------------
    public AppointmentDTO (Appointment appointment){
        this.id = appointment.getId();
        this.appointmentTime = appointment.getAppointmentTime();
        this.reason = appointment.getReason();
        if(appointment.getPatient() != null){
            this.patientId = appointment.getPatient().getId();
        }

        if(appointment.getDoctor() != null){
            this.doctorId = appointment.getDoctor().getId();
        }
    }
}
