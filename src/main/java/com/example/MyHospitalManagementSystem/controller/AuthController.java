package com.example.MyHospitalManagementSystem.controller;

import com.example.MyHospitalManagementSystem.dto.LoginRequestDTO;
import com.example.MyHospitalManagementSystem.dto.LoginResponseDTO;
import com.example.MyHospitalManagementSystem.dto.SignupRequestDTO;
import com.example.MyHospitalManagementSystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authService.login(loginRequestDTO));

    }

    @PostMapping("/signup")
    public ResponseEntity<SignupRequestDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO){
        return ResponseEntity.ok(authService.signup(signupRequestDTO));
    }

}
