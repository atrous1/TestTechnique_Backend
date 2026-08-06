package com.example.testtech.guide.dto;

import com.example.testtech.guide.entity.Audience;
import com.example.testtech.guide.entity.Mobility;
import com.example.testtech.guide.entity.Season;

import java.util.UUID;

public record GuideResponse(

        UUID id,
        String title,
        String description,
        Integer numberOfDays,
        Mobility mobility,
        Season season,
        Audience audience

) {
}