package com.example.MyHospitalManagementSystem.repository;

import com.example.MyHospitalManagementSystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}