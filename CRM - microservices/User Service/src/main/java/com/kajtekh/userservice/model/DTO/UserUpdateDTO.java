package com.kajtekh.userservice.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
        @Email(message = "Invalid email format")
        String email,

        @Size(min = 6, message = "Password must be at least 6 characters")
        String newPassword,

        @NotBlank(message = "Current password is required")
        String currentPassword
) {}
