package com.example.MyHospitalManagementSystem.dto;

import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.enums.BloodGroup;
import com.example.MyHospitalManagementSystem.enums.Gender;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
public class PatientDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String email;
    private Gender gender;
    private LocalDateTime createdAt;
    private BloodGroup bloodGroup;

    // Optional: simple info about insurance
    private InsuranceDTO insurance;

    // Optional: simple info about appointments
    private List<AppointmentDTO> appointments;
//    -------------------------------
    public PatientDTO(Patient patient){
        this.id = patient.getId();
        this.name = patient.getName();
        this.birthDate = patient.getBirthDate();
        this.email = patient.getEmail();
        this.gender = patient.getGender();
        this.createdAt = patient.getCreatedAt();
        this.bloodGroup = patient.getBloodGroup();

    }
}
