package com.example.testtech.guide.entity;

import com.example.testtech.activity.entity.Activity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.example.testtech.user.entity.User;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "guides")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false)
    private Integer numberOfDays;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Mobility mobility;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Season season;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Audience audience;

    @OneToMany(
            mappedBy = "guide",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<Activity> activities = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "guide_users",
            joinColumns = @JoinColumn(name = "guide_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Builder.Default
    private Set<User> invitedUsers = new HashSet<>();
}