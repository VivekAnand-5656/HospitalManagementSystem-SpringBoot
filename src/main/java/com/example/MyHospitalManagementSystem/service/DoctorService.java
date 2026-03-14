package com.example.MyHospitalManagementSystem.service;

import com.example.MyHospitalManagementSystem.dto.*;
import com.example.MyHospitalManagementSystem.entity.Appointment;
import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.enums.Department;
import com.example.MyHospitalManagementSystem.entity.Doctor;
import com.example.MyHospitalManagementSystem.repository.AppointmentRepository;
import com.example.MyHospitalManagementSystem.repository.DoctorRepository;
import com.example.MyHospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
//    ----- View Own Appointments ---------
//    @Transactional
//    public List<AppointmentRequestDTO> getMyAppointments(Long id){
//        List<Appointment> appointments = appointmentRepository.findAll();
//    }
//------------------------ All appointments -------
    @Transactional
    public List<AppointmentsResponseDTO> getAllAppointments(){
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<AppointmentsResponseDTO> appointmentDto = new ArrayList<>();
        for(Appointment appointment : appointmentList){
            appointmentDto.add(new AppointmentsResponseDTO(appointment));
        }
        return appointmentDto;
    }
//    --------------------------------------------------

    @Transactional
    public List<DoctorDTO> doctorList(){
        List<Doctor> doctor = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for(Doctor doctor1 : doctor){
            doctorDTOS.add(new DoctorDTO(doctor1));
        }
        return doctorDTOS;
    }

    @Transactional
    public DoctorDTO getByIdDoctor(Long id){
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Doctor in not available"));
        return new  DoctorDTO(doctor);
    }
//    --------------- Get Doctors By Departments -------
//----------------- Update Profile ------
    @Transactional
    public UpdateDoctorProfileResponseDTO updateDoctorProfile(UpdateDoctorProfileRequestDTO requestDTO){
        Doctor doctor = doctorRepository.findById(requestDTO.getUserId()).orElseThrow(()-> new RuntimeException("Doctor not exist"));

        if(requestDTO.getName() != null){
            doctor.setName(requestDTO.getName());
        }

        if(requestDTO.getSpecialization() != null){
            doctor.setSpecialization(requestDTO.getSpecialization());
        }
        if(requestDTO.getDepartmentsNames() != null){
            Set<Department> departments = requestDTO.getDepartmentsNames()
                    .stream()
                    .map(String::toUpperCase)
                    .map(Department::valueOf)
                    .collect(Collectors.toSet());
            doctor.setDepartments(departments);
        }

        Doctor doctorSave =  doctorRepository.save(doctor);
        return new UpdateDoctorProfileResponseDTO(doctorSave);
    }
//    ------------ Get Doctor By Departments ------
    @Transactional
    public Set<DoctorDTO> getDoctorByDepartments(String departments){
        Department department = Department.valueOf(departments.toUpperCase());
        List<Doctor> doctorList = doctorRepository.findAll();
        List<Doctor> filterDoctors =  new ArrayList<>();

        for(Doctor doctor : doctorList){
            if(doctor.getDepartments().contains(department)){
                filterDoctors.add(doctor);
            }
        }

        Set<DoctorDTO> doctorDTOS = new HashSet<>();
        for(Doctor doctor : filterDoctors){
            doctorDTOS.add(new DoctorDTO(doctor));
        }
        return doctorDTOS;
    }
// -------------------- Total Doctors Count ----------

    public Long totalDoctorNum(){
        long total = doctorRepository.count();
        return total;
    }

//    -------------- Total Patien Num -----------
    public Long totalPatientNum(){
        long total = patientRepository.count();
        return total;
    }
//    ----------- Total Appointments Num ---------
    public Long totalAppointmentsNum(){
        long total = appointmentRepository.count();
        return total;
    }
//    --------- Tital Patients ---------
    public List<PatientDTO> allPatients(){
        List<Patient> patients = patientRepository.findAll();
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for(Patient patient : patients){
            patientDTOList.add(new PatientDTO(patient));
        }
        return patientDTOList;
    }
}
