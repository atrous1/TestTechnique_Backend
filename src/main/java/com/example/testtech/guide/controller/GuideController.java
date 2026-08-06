package com.example.testtech.guide.controller;

import com.example.testtech.guide.dto.CreateGuideRequest;
import com.example.testtech.guide.dto.GuideResponse;
import com.example.testtech.guide.service.GuideService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/guides")
public class GuideController {

    private final GuideService guideService;

    public GuideController(GuideService guideService) {
        this.guideService = guideService;
    }

    @PostMapping
    public ResponseEntity<GuideResponse> createGuide(
            @Valid @RequestBody CreateGuideRequest request
    ) {
        GuideResponse response = guideService.createGuide(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<GuideResponse>> getGuides(
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                guideService.getGuidesForCurrentUser(authentication)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuideResponse> getGuideById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                guideService.getGuideById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuide(
            @PathVariable UUID id
    ) {
        guideService.deleteGuide(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{guideId}/users/{userId}")
    public ResponseEntity<Void> inviteUser(
            @PathVariable UUID guideId,
            @PathVariable UUID userId
    ) {
        guideService.inviteUser(guideId, userId);

        return ResponseEntity.noContent().build();
    }
}