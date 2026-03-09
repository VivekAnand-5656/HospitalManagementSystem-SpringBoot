package com.example.MyHospitalManagementSystem.entity;

import com.example.MyHospitalManagementSystem.enums.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    private User user;

    @Column( length = 100)
    private String name;

    @Column(length = 100)
    private String specialization;

    @Email
    @Column(unique = true,length = 100,updatable = false)
    private String email;

    @CollectionTable(name = "doctor_departments", joinColumns = @JoinColumn(name = "doctor_id"))
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Department.class)
    @Column(name = "department")
    private Set<Department> departments = new HashSet<>();

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Appointment> appointments = new ArrayList<>();


}
