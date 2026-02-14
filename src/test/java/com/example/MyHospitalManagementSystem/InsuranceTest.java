package com.example.MyHospitalManagementSystem;

import com.example.MyHospitalManagementSystem.entity.Appointment;
import com.example.MyHospitalManagementSystem.entity.Insurance;
import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.repository.AppointmentRepository;
import com.example.MyHospitalManagementSystem.repository.PatientRepository;
import com.example.MyHospitalManagementSystem.service.AppointmentService;
import com.example.MyHospitalManagementSystem.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class InsuranceTest {
    @Autowired
    private  InsuranceService insuranceService;
    @Autowired
    private  AppointmentService appointmentService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    public void testInsurance(){
        Insurance insurance = Insurance.builder().policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030,3,1))
                .build();
//        Patient patient = insuranceService.assignInsuranceToPatient(insurance,1L);
        System.out.println(patient);
    }

    private Appointment buildAppointment(String reason){
        return Appointment.builder()
                .appintmentTime(LocalDateTime.now())
                .reason(reason)
                .build();
    }
    @Test
    public void testCreateAppoinment(){

        List<Appointment> appointmentList = List.of(
                buildAppointment("Test"),
                buildAppointment("Dental Problem")
        );
        appointmentService.createNewAppoinment(appointmentList,4L,4L);

        System.out.println("Appointment Saved : ");

    }


}
