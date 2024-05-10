package com.riwi.beautySalon.domain.entity;

import com.riwi.beautySalon.utils.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column (nullable = false,unique = true,length = 150)
    private String userName;
    @Column(nullable = false,length = 150)
    private String password;
    @Enumerated(EnumType.STRINGs)
    private Role role;
}
