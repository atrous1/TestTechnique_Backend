package com.example.testtech.user.service;

import com.example.testtech.user.dto.CreateUserRequest;
import com.example.testtech.user.dto.UpdateUserRequest;
import com.example.testtech.user.dto.UserResponse;
import com.example.testtech.user.entity.User;
import com.example.testtech.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse createUser(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException(
                    "Cet email est déjà utilisé"
            );
        }

        User user = User.builder()
                .email(request.email())
                .password(
                        passwordEncoder.encode(request.password())
                )
                .role(request.role())
                .build();

        User savedUser = userRepository.save(user);

        return toResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public UserResponse updateUser(
            UUID id,
            UpdateUserRequest request
    ) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Utilisateur introuvable"
                        )
                );

        boolean emailChanged =
                !user.getEmail().equalsIgnoreCase(request.email());

        if (
                emailChanged
                        && userRepository.existsByEmail(request.email())
        ) {
            throw new IllegalArgumentException(
                    "Cet email est déjà utilisé"
            );
        }

        user.setEmail(request.email());
        user.setRole(request.role());

        User updatedUser = userRepository.save(user);

        return toResponse(updatedUser);
    }

    public void deleteUser(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Utilisateur introuvable"
                        )
                );

        userRepository.delete(user);
    }

    private UserResponse toResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}