package com.example.MyHospitalManagementSystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupRequestDTO {
    private String username;
    private String password;
}
