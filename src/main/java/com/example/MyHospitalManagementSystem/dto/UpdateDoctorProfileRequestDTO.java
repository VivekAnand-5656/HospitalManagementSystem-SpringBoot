package com.example.MyHospitalManagementSystem.dto;

import com.example.MyHospitalManagementSystem.enums.Department;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UpdateDoctorProfileRequestDTO {
    private Long userId;
    private String name;
    private String specialization;
    private Set<String> departmentsNames = new HashSet<>();

}
