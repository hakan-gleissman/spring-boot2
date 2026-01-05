package se.sprinto.hakan.springboot2.dto;

public record UserRequestDto(
        String username,
        String email,
        String password,
        String role,
        String displayName,
        String bio,
        String profileImagePath
) {
}
