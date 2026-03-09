package com.example.MyHospitalManagementSystem.service;

import com.example.MyHospitalManagementSystem.dto.LoginRequestDTO;
import com.example.MyHospitalManagementSystem.dto.LoginResponseDTO;
import com.example.MyHospitalManagementSystem.dto.SignupRequestDTO;
import com.example.MyHospitalManagementSystem.dto.SignupResponseDTO;
import com.example.MyHospitalManagementSystem.entity.Doctor;
import com.example.MyHospitalManagementSystem.entity.Patient;
import com.example.MyHospitalManagementSystem.entity.User;
import com.example.MyHospitalManagementSystem.enums.ProviderType;
import com.example.MyHospitalManagementSystem.enums.RoleType;
import com.example.MyHospitalManagementSystem.repository.DoctorRepository;
import com.example.MyHospitalManagementSystem.repository.PatientRepository;
import com.example.MyHospitalManagementSystem.repository.UserRepository;
import com.example.MyHospitalManagementSystem.security.AuthUtill;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final AuthUtill authUtill;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws Exception {

        AuthenticationManager authenticationManager =
                authenticationConfiguration.getAuthenticationManager();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getEmail(),
                        loginRequestDTO.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtill.generateAccessToken(user);

        return new LoginResponseDTO(token,user.getId());
    }
//    --------- Signup Internal ---
    public User signupInternal(SignupRequestDTO signupRequestDTO,ProviderType providerType,String providerId){
        User user = userRepository.findByEmail(signupRequestDTO.getEmail()).orElse(null);
        if(user != null) throw new IllegalArgumentException("User already exists");

        user = User.builder()
                .email(signupRequestDTO.getEmail())
                .providerId(providerId)
                .providerType(providerType)
                .roleTypes(signupRequestDTO.getRoleTypes())
                .build();
        if(providerType == ProviderType.EMAIL){
            user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        }

        user = userRepository.save(user);

        if(signupRequestDTO.getRoleTypes().contains(RoleType.DOCTOR)){
            Doctor doctor = Doctor.builder()
                    .name(signupRequestDTO.getName())
                    .email(signupRequestDTO.getEmail())
                    .user(user)
                    .build();

            doctorRepository.save(doctor);

        } else if (signupRequestDTO.getRoleTypes().contains(RoleType.PATIENT)) {
            Patient patient = Patient.builder()
                    .name(signupRequestDTO.getName())
                    .email(signupRequestDTO.getEmail())
                    .user(user)
                    .build();

            patientRepository.save(patient);

        } else if(signupRequestDTO.getRoleTypes().contains(RoleType.ADMIN)) {
            System.out.println("Admin Register Successfull");
        }
//        Patient patient = Patient.builder()
//                .name(signupRequestDTO.getName())
//                .email(signupRequestDTO.getEmail())
//                .user(user)
//                .build();




        return user;
    }

    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        User  user = signupInternal(signupRequestDTO,ProviderType.EMAIL,null);
        return new SignupResponseDTO(user.getId(),user.getUsername());
    }

    public ResponseEntity<LoginResponseDTO> handleOAuthLoginRequest(OAuth2User oAuth2User, String registrationId) {

        ProviderType providerType =
                authUtill.getProviderTypeRegistrationId(registrationId);

        String providerId =
                authUtill.determineProviderIdFromAuth2User(oAuth2User, registrationId);

        User user = userRepository
                .findByProviderIdAndProviderType(providerId, providerType)
                .orElse(null);
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");


        User emailUser = userRepository.findByEmail(email).orElse(null);

        if (user == null && emailUser == null) {
            String username = authUtill.determineUsernameFromAuth2User(oAuth2User,registrationId,providerId);
            SignupRequestDTO dto = new SignupRequestDTO();
            dto.getEmail();
            dto.setName(name);
            dto.setRoleTypes(Set.of(RoleType.PATIENT));
            user = signupInternal(
                    dto,
                    providerType,
                    providerId
            );
        } else if (user != null) {
            if(email != null && !email.isBlank() && !email.equals(user.getUsername())){
                user.getEmail();
                userRepository.save(user);
            }
        } else {
            throw new BadCredentialsException("This email is already exists");
        }

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(authUtill.generateAccessToken(user), user.getId());

        return ResponseEntity.ok(loginResponseDTO);
    }
}