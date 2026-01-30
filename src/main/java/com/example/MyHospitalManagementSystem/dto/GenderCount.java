package com.example.MyHospitalManagementSystem.dto;

import com.example.MyHospitalManagementSystem.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenderCount {
    private Gender gender;
    private Long total;
}
