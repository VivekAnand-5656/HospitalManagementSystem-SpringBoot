package com.example.MyHospitalManagementSystem.controller;

import com.example.MyHospitalManagementSystem.dto.DoctorDTO;
import com.example.MyHospitalManagementSystem.repository.DoctorRepository;
import com.example.MyHospitalManagementSystem.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorRepository doctorRepository;
    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getallDoctors(){
        return ResponseEntity.ok(doctorService.doctorList());
    }

    @GetMapping("id/{id}")
    public ResponseEntity<DoctorDTO> getByIdDoctor(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getByIdDoctor(id));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<DoctorDTO> getDoctorByName(@PathVariable String name){
        return ResponseEntity.ok(doctorService.getDoctorByName(name));
    }
}
