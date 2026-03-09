package com.example.MyHospitalManagementSystem.dto;

import com.example.MyHospitalManagementSystem.enums.BloodGroup;
import com.example.MyHospitalManagementSystem.enums.Gender;
import com.example.MyHospitalManagementSystem.enums.RoleType;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupRequestDTO {
    private String name;
    private String email;
    private String password;

    private Set<RoleType> roleTypes = new HashSet<>();


}
