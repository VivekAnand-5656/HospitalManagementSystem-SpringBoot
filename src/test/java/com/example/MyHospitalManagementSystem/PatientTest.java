package com.example.MyHospitalManagementSystem;

import com.example.MyHospitalManagementSystem.dto.BloodGroupCount;
import com.example.MyHospitalManagementSystem.dto.GenderCount;
import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.enums.BloodGroup;
import com.example.MyHospitalManagementSystem.enums.Gender;
import com.example.MyHospitalManagementSystem.repository.PatientRepository;
import com.example.MyHospitalManagementSystem.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTest {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository(){
        List<Patient> patientList = patientRepository.findAll();
        System.out.println(patientList);

        Patient p1 = new Patient();
        patientRepository.save(p1);
    }
    @Test
    public void testTransactionalMethod(){
//        Patient patient = patientService.getPatientById(2L);
//        System.out.println(patient);

//        Patient patient = patientRepository.findByBloodGroup(BloodGroup.O_POSITIVE);
//        System.out.println("Name Founded : "+patient);

//        List<Patient> patientList  = patientRepository.findByGender(Gender.MALE);
//        for(Patient p : patientList){
//            System.out.println(p);
//        }
//        ----- Write Queries -----
//        List<Patient> patientList = patientRepository.findByBloodGroupType(BloodGroup.AB_POSITIVE);
//        for(Patient p : patientList){
//            System.out.println(p);
//        }
//        List<Patient> patientList = patientRepository.findAllPatient();
//        List<Patient> patientList = patientRepository.findByBirth(LocalDate.of(1995,8,20));
//        for(Patient p : patientList){
//            System.out.println(p);
//        }
//        long count = patientRepository.countByBloodGroup(BloodGroup.O_POSITIVE);
//        System.out.println("Number of Patient of "+BloodGroup.O_POSITIVE+" : "+count);
//        List<Object[]> bloodGroupLists = patientRepository.countEachBloodGroup();
//        for(Object[] objects : bloodGroupLists){
//            System.out.println(objects[0] + " " + objects[1]);
//        }

//        ------ Updataion ------
//        int updateName = patientRepository.updateNameWithId("Sneha Anand","snehaAnand@gmail.com",7L);
//        System.out.println("Rows Updated : "+updateName);
//        int updateName = patientRepository.updateName("Ritu Rawat","Ritu Saxena");
//        System.out.println("Name Updated");

//        ----- Projection -----
//        List<BloodGroupCount> bloodGroupCounts = patientRepository.countPatientByBloodGroup();
//        for(BloodGroupCount bloodGroupCount : bloodGroupCounts){
//            System.out.println(bloodGroupCount);
//        }
//        ---- Count Patient By Gender ----
//        List<GenderCount> genderCounts = patientRepository.countPatientByGender();
//        for (GenderCount genderCount : genderCounts){
//            System.out.println(genderCount);
//        }
        Page<Patient> patientList = patientRepository.findAllPatient(PageRequest.of(2,10, Sort.by("birthDate").ascending()));
        for(Patient patient : patientList){
            System.out.println(patient);
        }
        System.out.println("Current Page : " + patientList.getNumber());
        System.out.println("Page Size    : " + patientList.getSize());
        System.out.println("Total Pages  : " + patientList.getTotalPages());
        System.out.println("Total Records: " + patientList.getTotalElements());
        System.out.println("Is First     : " + patientList.isFirst());
        System.out.println("Is Last      : " + patientList.isLast());
        System.out.println("Has Next     : " + patientList.hasNext());
        System.out.println("Has Previous : " + patientList.hasPrevious());

    }
}
