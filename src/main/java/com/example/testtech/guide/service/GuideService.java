package com.example.testtech.guide.service;

import com.example.testtech.guide.dto.CreateGuideRequest;
import com.example.testtech.guide.dto.GuideResponse;
import com.example.testtech.guide.entity.Guide;
import com.example.testtech.guide.repository.GuideRepository;
import com.example.testtech.user.entity.User;
import com.example.testtech.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GuideService {

    private final GuideRepository guideRepository;
    private final UserRepository userRepository;

    public GuideService(
            GuideRepository guideRepository,
            UserRepository userRepository
    ) {
        this.guideRepository = guideRepository;
        this.userRepository = userRepository;
    }

    public GuideResponse createGuide(CreateGuideRequest request) {

        Guide guide = Guide.builder()
                .title(request.title())
                .description(request.description())
                .numberOfDays(request.numberOfDays())
                .mobility(request.mobility())
                .season(request.season())
                .audience(request.audience())
                .build();

        Guide savedGuide = guideRepository.save(guide);

        return toResponse(savedGuide);
    }

    public List<GuideResponse> getGuidesForCurrentUser(
            Authentication authentication
    ) {
        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(authority ->
                        authority.getAuthority().equals("ROLE_ADMIN")
                );

        List<Guide> guides;

        if (isAdmin) {
            guides = guideRepository.findAll();
        } else {
            guides = guideRepository.findByInvitedUsersEmail(
                    authentication.getName()
            );
        }

        return guides.stream()
                .map(this::toResponse)
                .toList();
    }

    public GuideResponse getGuideById(UUID id) {

        Guide guide = guideRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Guide introuvable")
                );

        return toResponse(guide);
    }

    public void deleteGuide(UUID id) {

        Guide guide = guideRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Guide introuvable")
                );

        guideRepository.delete(guide);
    }

    public void inviteUser(UUID guideId, UUID userId) {

        Guide guide = guideRepository.findById(guideId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Guide introuvable")
                );

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Utilisateur introuvable")
                );

        if (guide.getInvitedUsers().contains(user)) {
            throw new IllegalArgumentException(
                    "Cet utilisateur est déjà invité à ce guide"
            );
        }

        guide.getInvitedUsers().add(user);

        guideRepository.save(guide);
    }

    private GuideResponse toResponse(Guide guide) {
        return new GuideResponse(
                guide.getId(),
                guide.getTitle(),
                guide.getDescription(),
                guide.getNumberOfDays(),
                guide.getMobility(),
                guide.getSeason(),
                guide.getAudience()
        );
    }
}