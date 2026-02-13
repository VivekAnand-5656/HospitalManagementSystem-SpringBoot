package com.example.MyHospitalManagementSystem.controller;


import com.example.MyHospitalManagementSystem.dto.PatientDTO;
import com.example.MyHospitalManagementSystem.dto.UpdateRequest;
import com.example.MyHospitalManagementSystem.repository.PatientRepository;
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

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllStudents());
    }

//    ----- Find By Id ----
    @GetMapping("/id/{id}")
    public ResponseEntity<PatientDTO> findByIDPatient(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PatientDTO> getPatientByName(@PathVariable String name){
        return ResponseEntity.ok(patientService.getPatientByName(name));
    }
//    ---- Create Patient -----
    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody @Valid PatientDTO dto){
//        return patientService.createPatient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(dto));
    }

    @PatchMapping("/update-name")
    public ResponseEntity<PatientDTO> updateByName(@RequestBody UpdateRequest request){
        return ResponseEntity.ok(patientService.updatePatientByName(request.getName(),request.getNewName()));
    }

//    ------- Delete ----
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable Long id){
        patientService.deletePatientById(id);
        return ResponseEntity.ok("Patient Deleted Successfully :=> "+id);
    }
}
