package com.example.testtech.user.dto;

import com.example.testtech.user.entity.Role;
import java.util.UUID;
public record UserResponse(
        UUID id,
        String email,
        Role role
) {
}