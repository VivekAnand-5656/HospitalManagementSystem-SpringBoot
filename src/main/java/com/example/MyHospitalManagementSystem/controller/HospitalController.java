package com.example.MyHospitalManagementSystem.controller;

import com.example.MyHospitalManagementSystem.dto.DoctorDTO;
import com.example.MyHospitalManagementSystem.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDTO>> allDoctors(){
        return ResponseEntity.ok(hospitalService.allDoctors());
    }
}
