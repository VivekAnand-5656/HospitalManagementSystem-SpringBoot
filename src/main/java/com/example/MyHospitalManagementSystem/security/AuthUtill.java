package com.example.MyHospitalManagementSystem.security;

import com.example.MyHospitalManagementSystem.entity.User;
import com.example.MyHospitalManagementSystem.enums.ProviderType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class AuthUtill {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }
    public String generateAccessToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId",user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(getSecretKey())
                .compact();
    }

    public String getUserNameFromToken(String token) {
    Claims claims =   Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();

    }
    public ProviderType getProviderTypeRegistrationId(String registrationId){
        return switch (registrationId.toLowerCase()){
            case "google" -> ProviderType.GOOGLE;
            case "github" -> ProviderType.GITHUB;
            case "facebook" -> ProviderType.FACEBOOK;
            case "twitter" -> ProviderType.TWITTER;
            default -> throw new IllegalArgumentException("Unsupported OAuth2 provider : "+registrationId);
        };
    }

    public String  determineProviderIdFromAuth2User(OAuth2User auth2User,String registrationId){
        String providerID = switch (registrationId.toLowerCase()){
            case "google"-> OAuth2User.getAttribute("sub");
            case "github"-> OAuth2User.getAttribute("id").toString();
            default -> {
                log.error("Unsupported OAuth2 provider : {}",registrationId);
                throw new IllegalArgumentException("Unsupported OAuth2 provider");
            }
        };

        if (providerID == null || providerID.isBlank()){
            log.error("Unable to determined providerId for provider: {}",registrationId);
            throw new IllegalArgumentException("Unable to determined providerId for OAuth2 login");
        }
        return providerID;
    }






}
