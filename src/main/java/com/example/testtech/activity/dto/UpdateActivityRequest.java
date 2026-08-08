package com.example.testtech.activity.dto;

import com.example.testtech.activity.entity.ActivityCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateActivityRequest(

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        ActivityCategory category,

        @NotBlank
        String address,

        String phone,

        String openingHours,

        String website,

        @NotNull
        @Positive
        Integer dayNumber,

        @NotNull
        @Positive
        Integer visitOrder

) {
}