package com.example.MyHospitalManagementSystem.controller;

import com.example.MyHospitalManagementSystem.dto.DoctorDTO;
import com.example.MyHospitalManagementSystem.dto.UpdateDoctorProfileRequestDTO;
import com.example.MyHospitalManagementSystem.dto.UpdateDoctorProfileResponseDTO;
import com.example.MyHospitalManagementSystem.repository.DoctorRepository;
import com.example.MyHospitalManagementSystem.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Set;

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
    @PostMapping("/update-profile")
    public ResponseEntity<UpdateDoctorProfileResponseDTO> updateDoctorProfile(
            @Valid @RequestBody UpdateDoctorProfileRequestDTO requestDTO ){
        return ResponseEntity.ok(doctorService.updateDoctorProfile(requestDTO));
    }
//    ------- Get All Doctors --------
    @GetMapping("/allDoctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctor(){
        return ResponseEntity.ok(doctorService.doctorList());
    }
//    -------- Get Doctor By Department -------
    @GetMapping("/doctor/department/{department}")
    public ResponseEntity<Set<DoctorDTO>> getDoctorByDepartment(@PathVariable String department){
        return ResponseEntity.ok(doctorService.getDoctorByDepartments(department));
    }

}
