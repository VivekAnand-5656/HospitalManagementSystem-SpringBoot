package com.example.MyHospitalManagementSystem.dto;

import com.example.MyHospitalManagementSystem.entity.Appointment;
import com.example.MyHospitalManagementSystem.enums.Department;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentRequestDTO {

    private Department department;

    @NotNull(message = "Appointment Date is Required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string",example = "2026-03-02")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment Time is required")
    @JsonFormat(pattern = "HH:mm")
    @Schema(type = "string", example = "10:30")
    private LocalTime appointmentTime;

    @NotBlank(message = "Reason is required")
    private String reason;



}
