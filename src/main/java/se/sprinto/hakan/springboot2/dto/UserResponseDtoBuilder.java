package se.sprinto.hakan.springboot2.dto;

public final class UserResponseDtoBuilder {
    private Long id;
    private String username;
    private String email;
    private String role;
    private String displayName;
    private String bio;
    private String profileImagePath;

    private UserResponseDtoBuilder() {
    }

    public static UserResponseDtoBuilder builder() {
        return new UserResponseDtoBuilder();
    }

    public UserResponseDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserResponseDtoBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserResponseDtoBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserResponseDtoBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    public UserResponseDtoBuilder withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public UserResponseDtoBuilder withBio(String bio) {
        this.bio = bio;
        return this;
    }

    public UserResponseDtoBuilder withProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
        return this;
    }

    public UserResponseDto build() {
        return new UserResponseDto(
                id,
                username,
                email,
                role,
                displayName,
                bio,
                profileImagePath
        );
    }
}

