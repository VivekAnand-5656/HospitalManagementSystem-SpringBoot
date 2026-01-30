package com.example.MyHospitalManagementSystem.entity;

import com.example.MyHospitalManagementSystem.enums.BloodGroup;
import com.example.MyHospitalManagementSystem.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_patient_email",columnNames = {"email"})
        },
        indexes = {
                @Index(name = "idx_patient_email", columnList = "email")
        }
)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is Required")
    @Column(length = 40)
    private String name;

    @NotNull
    private LocalDate birthDate;

    @NotBlank(message = "Email is Required")
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "blood_group")
    private BloodGroup bloodGroup;


}
