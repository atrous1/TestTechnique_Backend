package com.example.testtech.user.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import com.example.testtech.guide.entity.Guide;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @ManyToMany(mappedBy = "invitedUsers")
    @Builder.Default
    private Set<Guide> guides = new HashSet<>();
}