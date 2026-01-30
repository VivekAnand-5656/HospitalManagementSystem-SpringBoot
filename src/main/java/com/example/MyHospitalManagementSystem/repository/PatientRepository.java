package com.example.MyHospitalManagementSystem.repository;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import com.example.MyHospitalManagementSystem.dto.BloodGroupCount;
import com.example.MyHospitalManagementSystem.dto.GenderCount;
import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.enums.BloodGroup;
import com.example.MyHospitalManagementSystem.enums.Gender;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByName(String patientName);
    Patient findByBloodGroup(BloodGroup bloodGroup);
    List<Patient> findByGender(Gender gender);

//    ---- Query Type ----
    @Query("SELECT p FROM Patient p WHERE p.bloodGroup = ?1 ")
    List<Patient> findByBloodGroupType(@Param("bloodGroup") BloodGroup bloodGroup);

    @Query("SELECT p FROM Patient p")
//    List<Patient> findAllPatient();
    Page<Patient> findAllPatient(Pageable pageable);
    @Query("SELECT p FROM Patient p WHERE p.birthDate  > :birthDate")
    List<Patient> findByBirth(@Param("birthDate")LocalDate birthDate);

    @Query("SELECT COUNT(p) FROM Patient p WHERE p.bloodGroup = :bloodGroup")
    long countByBloodGroup(@Param("bloodGroup") BloodGroup bloodGroup);

    @Query("SELECT p.bloodGroup, COUNT(p) FROM Patient p GROUP BY p.bloodGroup ")
    List<Object[]> countEachBloodGroup();

//    ------- Updataion Queries -------
    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name,p.email = :email WHERE p.id = :id")
    int updateNameWithId(@Param("name") String name,@Param("email") String email,@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :updateName WHERE p.name = :name")
    int updateName(@Param("updateName")String updateName,@Param("name")String name);

//    ---- Projection ----
    @Query("SELECT new com.example.MyHospitalManagementSystem.dto.BloodGroupCount(p.bloodGroup , COUNT(p)) FROM Patient p GROUP BY p.bloodGroup ")
    List<BloodGroupCount> countPatientByBloodGroup();

//   ----- Gender Count -----
    @Query("SELECT new com.example.MyHospitalManagementSystem.dto.GenderCount(p.gender, COUNT(p)) FROM Patient p GROUP BY p.gender ")
    List<GenderCount> countPatientByGender();
}
