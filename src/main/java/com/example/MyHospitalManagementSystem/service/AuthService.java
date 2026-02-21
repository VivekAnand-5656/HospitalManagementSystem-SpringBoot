package com.example.MyHospitalManagementSystem.service;

import com.example.MyHospitalManagementSystem.dto.LoginRequestDTO;
import com.example.MyHospitalManagementSystem.dto.LoginResponseDTO;
import com.example.MyHospitalManagementSystem.dto.SignupRequestDTO;
import com.example.MyHospitalManagementSystem.entity.User;
import com.example.MyHospitalManagementSystem.enums.ProviderType;
import com.example.MyHospitalManagementSystem.enums.RoleType;
import com.example.MyHospitalManagementSystem.repository.UserRepository;
import com.example.MyHospitalManagementSystem.security.AuthUtill;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
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

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws Exception {

        AuthenticationManager authenticationManager =
                authenticationConfiguration.getAuthenticationManager();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtill.generateAccessToken(user);

        return new LoginResponseDTO(token, user.getId());
    }

    public SignupRequestDTO signup(SignupRequestDTO signupRequestDTO) {

        userRepository.findByUsername(signupRequestDTO.getUsername())
                .ifPresent(u -> { throw new IllegalArgumentException("User already exists"); });

        if (signupRequestDTO.getPassword() == null) {
            throw new RuntimeException("Password cannot be null");
        }

        int i=0;
        User user = User.builder()
                .username(signupRequestDTO.getUsername())
                .password(passwordEncoder.encode(signupRequestDTO.getPassword()))
                .providerType(ProviderType.EMAIL)
                .providerId("222"+i++)
                .roleTypes(Set.of(RoleType.PATIENT))
                .build();

        user = userRepository.save(user);

        return new SignupRequestDTO(user.getUsername(), user.getPassword());
    }

    public User handleOAuthLoginRequest(OAuth2User oAuth2User, String registrationId) {

        ProviderType providerType =
                authUtill.getProviderTypeRegistrationId(registrationId);

        String providerId =
                authUtill.determineProviderIdFromAuth2User(oAuth2User, registrationId);

        User user = userRepository
                .findByProviderIdAndProviderType(providerId, providerType)
                .orElse(null);

        if (user != null) {
            return user;
        }

        String username =
                authUtill.determineUsernameFromAuth2User(oAuth2User, registrationId, providerId);

        User newUser = User.builder()
                .username(username)
                .providerId(providerId)
                .providerType(providerType)
                .password(passwordEncoder.encode("Vivek@5656"))
                .build();

        return userRepository.save(newUser);
    }
}