package com.example.MyHospitalManagementSystem.controller;

import com.example.MyHospitalManagementSystem.dto.AppointmentDTO;
import com.example.MyHospitalManagementSystem.dto.DoctorDTO;
import com.example.MyHospitalManagementSystem.dto.InsuranceDTO;
import com.example.MyHospitalManagementSystem.dto.PatientDTO;
import com.example.MyHospitalManagementSystem.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final PatientService patientService;
    private final DoctorService doctorService;
    private final InsuranceService insuranceService;
    private final AppointmentService appointmentService;
    private final AdminService adminService;

//    ------ Appoiintments
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments(){
        return ResponseEntity.ok(adminService.getAllAppointments());
    }

//    -------- Doctors -----
    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDTO>> getallDoctors(){
        return ResponseEntity.ok(adminService.getAllDoctors());
    }

//    ------- Patients -----
    @GetMapping("/patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients(){
        return ResponseEntity.ok(adminService.getAllPatietns());
    }

    @DeleteMapping("/patient/delete/{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable Long id){
        adminService.deletePatientById(id);
        return ResponseEntity.ok("Patient Deleted Successfully :=> "+id);
    }
    @GetMapping("/patient/name/{name}")
    public ResponseEntity<PatientDTO> getPatientByName(@PathVariable String name){
        return ResponseEntity.ok(adminService.getPatientByName(name));
    }
//    --------- Insurance
    @GetMapping("/insurance")
    public ResponseEntity<List<InsuranceDTO>> getAllInsurance(){
         return ResponseEntity.ok(adminService.getAllInsurance());
}

}
