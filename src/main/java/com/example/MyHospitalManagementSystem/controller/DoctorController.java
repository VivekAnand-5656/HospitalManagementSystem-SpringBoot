package com.example.MyHospitalManagementSystem.controller;

import com.example.MyHospitalManagementSystem.dto.*;
import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.repository.DoctorRepository;
import com.example.MyHospitalManagementSystem.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Set;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/doctor/id/{id}")
    public ResponseEntity<DoctorDTO> getByIdDoctor(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getByIdDoctor(id));
    }

//    ==== Update Profile ====
    @PostMapping("/doctor/update-profile")
    public ResponseEntity<UpdateDoctorProfileResponseDTO> updateDoctorProfile(
            @Valid @RequestBody UpdateDoctorProfileRequestDTO requestDTO ){
        return ResponseEntity.ok(doctorService.updateDoctorProfile(requestDTO));
    }
//    ------- Get All Doctors --------
    @GetMapping("/doctor/allDoctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctor(){
        return ResponseEntity.ok(doctorService.doctorList());
    }
//    -------- Get Doctor By Department -------
    @GetMapping("/doctor/department/{department}")
    public ResponseEntity<Set<DoctorDTO>> getDoctorByDepartment(@PathVariable String department){
        return ResponseEntity.ok(doctorService.getDoctorByDepartments(department));
    }
//    -------- totals ---
//    --------- Doctor Num ---------
    @GetMapping("/doctor/allDoctorNum")
    public ResponseEntity<Long> allDoctorNum(){
        return ResponseEntity.ok(doctorService.totalDoctorNum());
    }
//    ---------- Patients Num ----
    @GetMapping("/doctor/allPatientNum")
    public ResponseEntity<Long> allPatientNum(){
        return ResponseEntity.ok(doctorService.totalPatientNum());
    }
    //    ---------- Appointments Num ----
    @GetMapping("/doctor/allAppointmentNum")
    public ResponseEntity<Long> allAppointmentsNum(){
        return ResponseEntity.ok(doctorService.totalAppointmentsNum());
    }
//    ------------ All Appointments ----------
    @GetMapping("/doctor/allAppointments")
    public ResponseEntity<List<AppointmentsResponseDTO>> getAllAppointments(){
        return ResponseEntity.ok(doctorService.getAllAppointments());
    }
//    ----------- All Patients --------
    @GetMapping("/doctor/allPatients")
    public ResponseEntity<List<PatientDTO>> allPatients(){
        return ResponseEntity.ok(doctorService.allPatients());
    }


}
