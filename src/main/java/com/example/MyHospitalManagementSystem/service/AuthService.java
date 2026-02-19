package com.example.MyHospitalManagementSystem.service;

import com.example.MyHospitalManagementSystem.config.AppConfig;
import com.example.MyHospitalManagementSystem.dto.LoginRequestDTO;
import com.example.MyHospitalManagementSystem.dto.LoginResponseDTO;
import com.example.MyHospitalManagementSystem.dto.SignupRequestDTO;
import com.example.MyHospitalManagementSystem.entity.User;
import com.example.MyHospitalManagementSystem.enums.ProviderType;
import com.example.MyHospitalManagementSystem.repository.UserRepository;
import com.example.MyHospitalManagementSystem.security.AuthUtill;
import jakarta.security.auth.message.config.AuthConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final AuthUtill authUtill;
    private final AppConfig appConfig;
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();
//        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(()-> new RuntimeException("User not found"));
        String token = authUtill.generateAccessToken(user);

        return new LoginResponseDTO(token,user.getId());

    }


    public SignupRequestDTO signup(SignupRequestDTO signupRequestDTO) {
        userRepository.findByUsername(signupRequestDTO.getUsername())
                .ifPresent(u -> { throw new IllegalArgumentException("User already exists"); });

        if (signupRequestDTO.getPassword() == null) {
            throw new RuntimeException("Password cannot be null");
        }

        User user = User.builder()
                .username(signupRequestDTO.getUsername())
                .password(appConfig.passwordEncoder().encode(signupRequestDTO.getPassword()))
                .build();

        user = userRepository.save(user);

        return new SignupRequestDTO(user.getUsername(), user.getPassword());
    }

    public ResponseEntity<LoginResponseDTO> handleOAuthLoginRequest(OAuth2User oAuth2User, String registrationId) {
        ProviderType providerType = authUtill.getProviderTypeRegistrationId(registrationId);
        String providerId = authUtill.determineProviderIdFromAuth2User(oAuth2User,registrationId);
        User user = userRepository.findByProviderIdAndProviderType(providerId,providerType);
    }
}
