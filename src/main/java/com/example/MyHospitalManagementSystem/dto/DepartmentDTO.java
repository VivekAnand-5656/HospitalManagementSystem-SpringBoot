package com.example.MyHospitalManagementSystem.dto;

import com.example.MyHospitalManagementSystem.entity.Department;
import com.example.MyHospitalManagementSystem.entity.Doctor;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentDTO {
    private Long id;

    private String name;

    private Doctor doctor;

    private Set<Doctor> doctors = new HashSet<>();
//    ------------
    public DepartmentDTO(Department department){
        this.id = department.getId();
        this.name = department.getName();
        this.doctor = department.getDoctor();
        this.doctors = department.getDoctors();
    }
}
