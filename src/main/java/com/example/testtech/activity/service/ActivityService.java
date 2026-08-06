package com.example.testtech.activity.service;

import com.example.testtech.activity.dto.ActivityResponse;
import com.example.testtech.activity.dto.CreateActivityRequest;
import com.example.testtech.activity.entity.Activity;
import com.example.testtech.activity.repository.ActivityRepository;
import com.example.testtech.guide.entity.Guide;
import com.example.testtech.guide.repository.GuideRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final GuideRepository guideRepository;

    public ActivityService(
            ActivityRepository activityRepository,
            GuideRepository guideRepository
    ) {
        this.activityRepository = activityRepository;
        this.guideRepository = guideRepository;
    }

    public ActivityResponse createActivity(
            UUID guideId,
            CreateActivityRequest request
    ) {
        Guide guide = guideRepository.findById(guideId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Guide introuvable")
                );

        if (request.dayNumber() > guide.getNumberOfDays()) {
            throw new IllegalArgumentException(
                    "Le numéro du jour dépasse la durée du guide"
            );
        }

        Activity activity = Activity.builder()
                .title(request.title())
                .description(request.description())
                .category(request.category())
                .address(request.address())
                .phone(request.phone())
                .openingHours(request.openingHours())
                .website(request.website())
                .dayNumber(request.dayNumber())
                .visitOrder(request.visitOrder())
                .guide(guide)
                .build();

        Activity savedActivity = activityRepository.save(activity);

        return toResponse(savedActivity);
    }

    public List<ActivityResponse> getActivitiesByGuide(UUID guideId) {

        if (!guideRepository.existsById(guideId)) {
            throw new IllegalArgumentException("Guide introuvable");
        }

        return activityRepository
                .findByGuideIdOrderByDayNumberAscVisitOrderAsc(guideId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ActivityResponse getActivityById(
            UUID guideId,
            UUID activityId
    ) {
        Activity activity = getActivityFromGuide(guideId, activityId);

        return toResponse(activity);
    }

    public void deleteActivity(
            UUID guideId,
            UUID activityId
    ) {
        Activity activity = getActivityFromGuide(guideId, activityId);

        activityRepository.delete(activity);
    }

    private Activity getActivityFromGuide(
            UUID guideId,
            UUID activityId
    ) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Activité introuvable")
                );

        if (!activity.getGuide().getId().equals(guideId)) {
            throw new IllegalArgumentException(
                    "Cette activité n'appartient pas à ce guide"
            );
        }

        return activity;
    }

    private ActivityResponse toResponse(Activity activity) {
        return new ActivityResponse(
                activity.getId(),
                activity.getTitle(),
                activity.getDescription(),
                activity.getCategory(),
                activity.getAddress(),
                activity.getPhone(),
                activity.getOpeningHours(),
                activity.getWebsite(),
                activity.getDayNumber(),
                activity.getVisitOrder(),
                activity.getGuide().getId()
        );
    }
}