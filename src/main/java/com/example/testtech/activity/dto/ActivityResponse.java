package com.example.testtech.activity.dto;

import com.example.testtech.activity.entity.ActivityCategory;

import java.util.UUID;

public record ActivityResponse(

        UUID id,
        String title,
        String description,
        ActivityCategory category,
        String address,
        String phone,
        String openingHours,
        String website,
        Integer dayNumber,
        Integer visitOrder,
        UUID guideId

) {
}