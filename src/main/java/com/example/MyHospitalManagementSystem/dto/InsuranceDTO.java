package com.example.MyHospitalManagementSystem.dto;

import com.example.MyHospitalManagementSystem.entity.Insurance;
import com.example.MyHospitalManagementSystem.entity.Patient;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InsuranceDTO {
    private Long id;

    private String policyNumber;

    private String provider;

    private LocalDate validUntil;

    private LocalDateTime createdAt;

    private Patient patient;
//    -----------------------
    public InsuranceDTO(Insurance insurance){
        this.id = insurance.getId();
        this.policyNumber = insurance.getPolicyNumber();
        this.provider = insurance.getProvider();
        this.validUntil = insurance.getValidUntil();
        this.createdAt = insurance.getCreatedAt();
        this.patient = insurance.getPatient();
    }
}
