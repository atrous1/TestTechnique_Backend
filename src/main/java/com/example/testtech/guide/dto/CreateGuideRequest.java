package com.example.testtech.guide.dto;

import com.example.testtech.guide.entity.Audience;
import com.example.testtech.guide.entity.Mobility;
import com.example.testtech.guide.entity.Season;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateGuideRequest(

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        Integer numberOfDays,

        @NotNull
        Mobility mobility,

        @NotNull
        Season season,

        @NotNull
        Audience audience

) {
}