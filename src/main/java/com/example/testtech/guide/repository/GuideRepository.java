package com.example.testtech.guide.repository;

import com.example.testtech.guide.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface GuideRepository extends JpaRepository<Guide, UUID> {
    List<Guide> findByInvitedUsersEmail(String email);
}