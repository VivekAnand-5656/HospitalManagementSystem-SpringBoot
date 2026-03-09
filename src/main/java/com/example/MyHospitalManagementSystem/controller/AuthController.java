package com.example.MyHospitalManagementSystem.controller;

import com.example.MyHospitalManagementSystem.dto.LoginRequestDTO;
import com.example.MyHospitalManagementSystem.dto.LoginResponseDTO;
import com.example.MyHospitalManagementSystem.dto.SignupRequestDTO;
import com.example.MyHospitalManagementSystem.dto.SignupResponseDTO;
import com.example.MyHospitalManagementSystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    // ✅ LOGIN API
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO loginRequestDTO) throws Exception {

        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    // ✅ SIGNUP API
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(
            @RequestBody SignupRequestDTO signupRequestDTO) {

        return ResponseEntity.ok(authService.signup(signupRequestDTO));
    }
}