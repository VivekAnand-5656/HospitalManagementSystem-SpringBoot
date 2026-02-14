package com.example.MyHospitalManagementSystem.controller;

import com.example.MyHospitalManagementSystem.dto.InsuranceDTO;
import com.example.MyHospitalManagementSystem.repository.InsuranceRepository;
import com.example.MyHospitalManagementSystem.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;
    private final InsuranceRepository insuranceRepository;



    @PostMapping
    public ResponseEntity<InsuranceDTO> createInsurance(@RequestBody InsuranceDTO insuranceDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(insuranceService.assignInsuranceToPatient(insuranceDTO));

    }
}
