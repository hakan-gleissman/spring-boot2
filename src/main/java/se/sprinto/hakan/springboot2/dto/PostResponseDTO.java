package se.sprinto.hakan.springboot2.dto;

import java.time.LocalDateTime;

public record PostResponseDTO(Long id, String text, LocalDateTime createdAt, UserResponseDto user) {
}

