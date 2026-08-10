package com.example.testtech.user.dto;

import com.example.testtech.user.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(

        @NotBlank
        @Email
        String email,

        @NotNull
        Role role

) {
}