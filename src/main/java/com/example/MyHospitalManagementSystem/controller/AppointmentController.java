package com.example.MyHospitalManagementSystem.controller;

import com.example.MyHospitalManagementSystem.dto.AppointmentRequestDTO;
import com.example.MyHospitalManagementSystem.dto.AppointmentsResponseDTO;
import com.example.MyHospitalManagementSystem.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;



    @PostMapping
    public ResponseEntity<AppointmentsResponseDTO> createAppointment(@RequestBody @Valid AppointmentRequestDTO appointmentDTO,Long patientId){
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createAppointment(appointmentDTO,patientId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointmentById(id);
        return ResponseEntity.ok("Appointment Deleted Successfully");
    }

}
