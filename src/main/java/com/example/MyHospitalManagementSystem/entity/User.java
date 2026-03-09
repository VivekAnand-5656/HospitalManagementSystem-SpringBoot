package com.example.MyHospitalManagementSystem.entity;

import com.example.MyHospitalManagementSystem.enums.ProviderType;
import com.example.MyHospitalManagementSystem.enums.RoleType;
import com.example.MyHospitalManagementSystem.security.RolePermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder    // user keyword conflict avoid karne ke liye
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Login ke liye email hi username hai
    @Column(nullable = false, unique = true)
    private String email;

    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;  // LOCAL / GOOGLE etc

    private String providerId;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<RoleType> roleTypes = new HashSet<>();

    // ========================
    // Spring Security Methods
    // ========================

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roleTypes.stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
//                .toList();
        Set<GrantedAuthority> authorities = new HashSet<>();
        roleTypes.forEach(
                roleType -> {
//                    Set<GrantedAuthority> permissions = RolePermissionMapping.getAuthoritiesForRole(roleType);
                    Set<GrantedAuthority> permissions = RolePermissionMapping.getAuthoritiesForRole(roleType);
                    authorities.addAll(permissions);
                    authorities.add(new SimpleGrantedAuthority("ROLE_"+roleType.name()));
                }
        );
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;   // email hi username hai
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // future me logic laga sakte ho
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // future me account lock feature
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}