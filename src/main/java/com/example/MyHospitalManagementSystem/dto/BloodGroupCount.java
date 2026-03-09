package com.example.MyHospitalManagementSystem.dto;

import com.example.MyHospitalManagementSystem.enums.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodGroupCount {
    private BloodGroup bloodGroup;
    private Long count;
}
