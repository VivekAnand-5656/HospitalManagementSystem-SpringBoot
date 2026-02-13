package com.example.MyHospitalManagementSystem.repository;

import com.example.MyHospitalManagementSystem.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT p FROM Doctor p WHERE p.name=:name")
    Doctor getDoctorByName(@Param("name")String name);
}