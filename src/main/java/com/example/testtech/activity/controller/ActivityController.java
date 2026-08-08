package com.example.testtech.activity.controller;

import com.example.testtech.activity.dto.ActivityResponse;
import com.example.testtech.activity.dto.CreateActivityRequest;
import com.example.testtech.activity.dto.UpdateActivityRequest;
import com.example.testtech.activity.service.ActivityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/guides/{guideId}/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<ActivityResponse> createActivity(
            @PathVariable UUID guideId,
            @Valid @RequestBody CreateActivityRequest request
    ) {
        ActivityResponse response =
                activityService.createActivity(guideId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getGuideActivities(
            @PathVariable UUID guideId
    ) {
        return ResponseEntity.ok(
                activityService.getActivitiesByGuide(guideId)
        );
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivityById(
            @PathVariable UUID guideId,
            @PathVariable UUID activityId
    ) {
        return ResponseEntity.ok(
                activityService.getActivityById(
                        guideId,
                        activityId
                )
        );
    }

    @PutMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> updateActivity(
            @PathVariable UUID guideId,
            @PathVariable UUID activityId,
            @Valid @RequestBody UpdateActivityRequest request
    ) {
        return ResponseEntity.ok(
                activityService.updateActivity(
                        guideId,
                        activityId,
                        request
                )
        );
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<Void> deleteActivity(
            @PathVariable UUID guideId,
            @PathVariable UUID activityId
    ) {
        activityService.deleteActivity(
                guideId,
                activityId
        );

        return ResponseEntity.noContent().build();
    }
}