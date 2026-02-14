package com.example.MyHospitalManagementSystem.service;

import com.example.MyHospitalManagementSystem.dto.AppointmentDTO;
import com.example.MyHospitalManagementSystem.dto.DoctorDTO;
import com.example.MyHospitalManagementSystem.dto.InsuranceDTO;
import com.example.MyHospitalManagementSystem.dto.PatientDTO;
import com.example.MyHospitalManagementSystem.entity.Appointment;
import com.example.MyHospitalManagementSystem.entity.Doctor;
import com.example.MyHospitalManagementSystem.entity.Insurance;
import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.repository.AppointmentRepository;
import com.example.MyHospitalManagementSystem.repository.DoctorRepository;
import com.example.MyHospitalManagementSystem.repository.InsuranceRepository;
import com.example.MyHospitalManagementSystem.repository.PatientRepository;
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
//    ----------- Patient Functionality -------------
//    ------- Get All Patients ---

    public List<PatientDTO> getAllPatietns(){
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
    public void deletePatientById(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Pateint not exist"));
        patientRepository.delete(patient);
        System.out.println("Patient Deleted Successfully");
    }

//    ----- Appointments ----
     public List<AppointmentDTO> getAllAppointments(){
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();

        for(Appointment appointment : appointmentList){
            appointmentDTOS.add(new AppointmentDTO(appointment));
        }
        return appointmentDTOS;
     }
//     ------- Delete Appointments ------
    @Transactional
    public void deleteAppointment(Long id){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Appointments Not Exists"));
        appointmentRepository.delete(appointment);
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


}
