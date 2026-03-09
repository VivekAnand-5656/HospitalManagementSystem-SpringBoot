package com.example.MyHospitalManagementSystem.dto;

import com.example.MyHospitalManagementSystem.enums.BloodGroup;
import com.example.MyHospitalManagementSystem.enums.Gender;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePatientRequestDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String email;
    private Gender gender;
    private LocalDateTime createdAt;
    private BloodGroup bloodGroup;

}
