package se.sprinto.hakan.springboot2.dto;


import se.sprinto.hakan.springboot2.model.User;

public class UserResponseDto {

    private Long id;
    private String username;
    private String role;
    private String displayName;
    private String profileImagePath;
    private String bio;

    public static UserResponseDto fromEntity(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.id = user.getId();
        dto.username = user.getUsername();
        dto.role = user.getRole();
        dto.displayName = user.getDisplayName();
        dto.profileImagePath = user.getProfileImagePath();
        dto.bio = user.getBio();
        return dto;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public String getBio() {
        return bio;
    }
}

