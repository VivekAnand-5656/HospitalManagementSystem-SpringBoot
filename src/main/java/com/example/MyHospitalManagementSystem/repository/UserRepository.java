package com.example.MyHospitalManagementSystem.repository;

import com.example.MyHospitalManagementSystem.entity.User;
import com.example.MyHospitalManagementSystem.enums.ProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);

    Optional<User> findByProviderIdAndProviderType(String providerId, ProviderType providerType);

}