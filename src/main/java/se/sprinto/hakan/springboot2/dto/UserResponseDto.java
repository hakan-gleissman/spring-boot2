package se.sprinto.hakan.springboot2.dto;


import se.sprinto.hakan.springboot2.model.User;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String role,
        String displayName,
        String bio,
        String profileImagePath
) {

    public static UserResponseDto fromEntity(User user) {

        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getDisplayName(),
                user.getBio(),
                user.getProfileImagePath()
        );
    }
}
