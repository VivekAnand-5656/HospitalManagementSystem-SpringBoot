package com.example.MyHospitalManagementSystem.security;

import com.example.MyHospitalManagementSystem.entity.User;
import com.example.MyHospitalManagementSystem.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuthSeccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private final AuthService authService;
    private final AuthUtill authUtill;


    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {
//        OAuth2AccessToken token = (OAuth2AccessToken) authentication;
        OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String registrationId = auth2AuthenticationToken.getAuthorizedClientRegistrationId();
        OAuth2User oAuth2User = (OAuth2User) auth2AuthenticationToken.getPrincipal();

        User user = authService.handleOAuthLoginRequest(oAuth2User, registrationId);

        String jwt = authUtill.generateAccessToken(user);

        response.setContentType("application/json");
        response.getWriter().write(
                "{\"token\":\"" + jwt + "\"}"
        );

    }
}
