package com.example.MyHospitalManagementSystem.entity;

import com.example.MyHospitalManagementSystem.enums.BloodGroup;
import com.example.MyHospitalManagementSystem.enums.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString(exclude = {"appointments"})
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

    @OneToOne
    @MapsId
    private User user;

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

    @OneToOne(mappedBy = "patient", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    private Insurance insurance;

    @OneToMany(mappedBy = "patient",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JsonIgnore
    private List<Appointment> appointments = new ArrayList<>();



}
