package com.example.MyHospitalManagementSystem.controller;

import com.example.MyHospitalManagementSystem.dto.DoctorDTO;
import com.example.MyHospitalManagementSystem.repository.DoctorRepository;
import com.example.MyHospitalManagementSystem.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorRepository doctorRepository;
    private final DoctorService doctorService;



    @GetMapping("/doctors/id/{id}")
    public ResponseEntity<DoctorDTO> getByIdDoctor(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getByIdDoctor(id));
    }


}
