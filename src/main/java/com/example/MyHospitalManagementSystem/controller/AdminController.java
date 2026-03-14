package com.example.MyHospitalManagementSystem.controller;

import com.example.MyHospitalManagementSystem.dto.*;
import com.example.MyHospitalManagementSystem.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final PatientService patientService;
    private final DoctorService doctorService;
    private final InsuranceService insuranceService;
    private final AppointmentService appointmentService;
    private final AdminService adminService;

//    ------ Appoiintments
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentsResponseDTO>> getAllAppointments(){
        return ResponseEntity.ok(adminService.getAllAppointments());
    }
//    ====== Total Appointments Count -======
    @GetMapping("/totalAppointments")
    public ResponseEntity<Long> totalAppointments(){
        return ResponseEntity.ok(adminService.totalAppointments());
    }

//    -------- Doctors -----
    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDTO>> getallDoctors(){
        return ResponseEntity.ok(adminService.getAllDoctors());
    }
//    ====== Total Doctors Count =====
    @GetMapping("/totalDoctors")
    public ResponseEntity<Long> totalDoctorsNumbers(){
        return ResponseEntity.ok(adminService.totalDoctors());
    }

//    ------- Patients -----
    @GetMapping("/patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients(){
        return ResponseEntity.ok(adminService.getAllPatients());
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
//    ====== Total Patients =====
    @GetMapping("/totalPatients")
    public ResponseEntity<Long> totalPatientsNum(){
        return ResponseEntity.ok(adminService.totalPatients());
    }
//    --------- Insurance
    @GetMapping("/insurance")
    public ResponseEntity<List<InsuranceDTO>> getAllInsurance(){
         return ResponseEntity.ok(adminService.getAllInsurance());
    }

//    ============ Delete User =======
//    @DeleteMapping("user/role/{role}")
//    public ResponseEntity<String> deleteUserByRole(@PathVariable String role){
//        adminService.deleteUserByRole(role);
//        return ResponseEntity.ok("User Deleted Successfully");
//    }
}
