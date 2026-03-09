package com.example.MyHospitalManagementSystem.dto;


import com.example.MyHospitalManagementSystem.entity.Doctor;
import com.example.MyHospitalManagementSystem.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDoctorProfileResponseDTO {
    private Long id;
    private String name;
    private String specialization;
    private String email;
    private Set<String> departments;

    public UpdateDoctorProfileResponseDTO(Doctor doctor){
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.email = doctor.getEmail();
        this.specialization = doctor.getSpecialization();
        this.departments = doctor.getDepartments().stream()
                .map(Department::name)
                .collect(Collectors.toSet());
    }



}
