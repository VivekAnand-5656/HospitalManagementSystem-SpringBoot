package com.example.MyHospitalManagementSystem.dto;

import com.example.MyHospitalManagementSystem.entity.Appointment;
import com.example.MyHospitalManagementSystem.entity.Department;
import com.example.MyHospitalManagementSystem.entity.Doctor;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorDTO {
    private Long id;

    private String name;

    private String specialization;

    private String email;

    private Set<Department> departments = new HashSet<>();

    private List<Appointment> appointments = new ArrayList<>();
//    ---------------
    public DoctorDTO(Doctor d){
        this.id = d.getId();
        this.name = d.getName();
        this.specialization = d.getSpecialization();
        this.email = d.getEmail();
        this.departments = d.getDepartments();
        this.appointments = d.getAppointments();

    }
}
