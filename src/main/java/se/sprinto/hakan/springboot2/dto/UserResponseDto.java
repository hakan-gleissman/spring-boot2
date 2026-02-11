package se.sprinto.hakan.springboot2.dto;


public record UserResponseDto(
        Long id,
        String username,
        String email,
        String role,
        String displayName,
        String bio,
        String profileImagePath
) {


}
