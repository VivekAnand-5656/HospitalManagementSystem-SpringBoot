package com.example.MyHospitalManagementSystem.controller;


import com.example.MyHospitalManagementSystem.dto.*;
import com.example.MyHospitalManagementSystem.repository.PatientRepository;
import com.example.MyHospitalManagementSystem.service.AppointmentService;
import com.example.MyHospitalManagementSystem.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    private final PatientRepository patientRepository;
    private final PatientService patientService;
    private final AppointmentService appointmentService;



    @PatchMapping("/update-name")
    public ResponseEntity<PatientDTO> updateByName(@RequestBody UpdateRequest request){
        return ResponseEntity.ok(patientService.updatePatientByName(request.getName(),request.getNewName()));
    }

    @GetMapping("patient/id/{id}")
    public ResponseEntity<PatientDTO> findPatientById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.findById(id));
    }

//    ---------- Update Patient Profile ----------
    @PutMapping("/patient/update-profile")
    public ResponseEntity<UpdatePatientResponseDTO> updatePatientPRofile(@RequestBody UpdatePatientRequestDTO requestDTO){
        return ResponseEntity.ok(patientService.updatePatientResponseDTO(requestDTO));
    }

//    ============ Create Appointment ======
    @PostMapping("/patient/create-appointment")
    public ResponseEntity<AppointmentsResponseDTO> createAppointment(@RequestBody AppointmentRequestDTO requestDTO,Long patientId){
        return ResponseEntity.ok(appointmentService.createAppointment(requestDTO,patientId));
    }

//    ====== Cancel Appointment ======
    @PutMapping("/cancel-appointment/{appointmentId}")
    public ResponseEntity<String> cancelAppointment(@PathVariable Long appointmentId){
        appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.ok("Appointment cancelled successfully");
    }

}
