package com.example.MyHospitalManagementSystem.service;

import com.example.MyHospitalManagementSystem.dto.AppointmentRequestDTO;
import com.example.MyHospitalManagementSystem.dto.DoctorDTO;
import com.example.MyHospitalManagementSystem.dto.InsuranceDTO;
import com.example.MyHospitalManagementSystem.dto.PatientDTO;
import com.example.MyHospitalManagementSystem.entity.*;
import com.example.MyHospitalManagementSystem.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final InsuranceRepository insuranceRepository;
    private final UserRepository userRepository;

//    ------ Doctor Functionality ----------
//    ------ Get All Doctors -----
    public List<DoctorDTO> getAllDoctors(){
        List<Doctor> doctorList = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOS = new ArrayList<>();
        for(Doctor doctor : doctorList){
            doctorDTOS.add(new DoctorDTO(doctor));
        }
        return doctorDTOS;
    }
//    ----------- Add Doctor --------
    @Transactional
    public DoctorDTO createDoctor(Doctor doctor){
        Doctor newDoctor = new Doctor();
        newDoctor.setName(doctor.getName());
        newDoctor.setEmail(doctor.getEmail());
        newDoctor.setSpecialization(doctor.getSpecialization());
        newDoctor.setDepartments(doctor.getDepartments());

        Doctor saveDoctor = doctorRepository.save(newDoctor);

        return new DoctorDTO(saveDoctor);
    }
//    -------------- Delete Doctor ------------
    @Transactional
    public void deleteDoctorById(Long id){
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Doctor Not Exists"));
        doctorRepository.delete(doctor);
        System.out.println("Doctor Deleted Successfully");
    }
//    ============== Total Doctor ===========
    public Long totalDoctors(){
         long totalNum = doctorRepository.count();
         return totalNum;

    }
//    ----------- Patient Functionality -------------
//    ------- Get All Patients ---

    public List<PatientDTO> getAllPatients(){
        List<Patient> patientList = patientRepository.findAll();
        List<PatientDTO> patientDTOS = new ArrayList<>();

        for(Patient patient : patientList){
            patientDTOS.add(new PatientDTO(patient));
        }
        return patientDTOS;
    }
    @Transactional
    public PatientDTO getPatientById(Long id){
        Patient p1 = patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Patient not found"));

        return new  PatientDTO(p1);
    }
    @Transactional
    public PatientDTO getPatientByName(String name){
        Patient patient = patientRepository.findPatientByName(name);
        return new PatientDTO(patient);

    }
//    ---------- Delete Patient -----
    @Transactional
    public void deletePatientById(Long userId){
        patientRepository.deletePatientById(userId);
 //        User user = userRepository.findById(patient.getId()).orElseThrow(()-> new EntityNotFoundException("User not found"));
//        userRepository.delete(patient.getUser());
        System.out.println("Patient Deleted Successfully");
    }
//    ============= Total Patients ==========
    public Long totalPatients(){
        long totalPatient = patientRepository.count();
        return totalPatient;
    }

//    ----- Appointments ----
     public List<AppointmentRequestDTO> getAllAppointments(){
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<AppointmentRequestDTO> appointmentDTOS = new ArrayList<>();

        for(Appointment appointment : appointmentList){
//            appointmentDTOS.add(new AppointmentRequestDTO(appointment));
        }
        return appointmentDTOS;
     }
//     ------- Delete Appointments ------
    @Transactional
    public void deleteAppointment(Long id){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Appointments Not Exists"));
        appointmentRepository.delete(appointment);
    }
//    ==== Total Appointments ====
    public Long totalAppointments(){
        long totalAp = appointmentRepository.count();
        return totalAp;
    }
//    ----- Insurance ---
public List<InsuranceDTO> getAllInsurance(){
    List<Insurance> insurances = insuranceRepository.findAll();
    List<InsuranceDTO> insuranceDTOS = new ArrayList<>();

    for(Insurance insurance : insurances){
        insuranceDTOS.add(new InsuranceDTO(insurance));
    }

    return insuranceDTOS;
}
//          Patient delete left -----------------
// =============== Delete User's ===============
//    public void deleteUserByRole(String role){
//        User user = userRepository.findByRole(role).orElseThrow(()-> new EntityNotFoundException("User not exist."));
//        userRepository.delete(user);
//        System.out.println("User Deleted Successfully");
//
//    }

}
