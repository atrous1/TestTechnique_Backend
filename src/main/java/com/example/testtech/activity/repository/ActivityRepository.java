package com.example.testtech.activity.repository;

import com.example.testtech.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {

    List<Activity> findByGuideIdOrderByDayNumberAscVisitOrderAsc(UUID guideId);
}