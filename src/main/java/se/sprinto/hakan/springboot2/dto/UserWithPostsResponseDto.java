package se.sprinto.hakan.springboot2.dto;

import java.util.List;

public record UserWithPostsResponseDto(
        UserResponseDto user,
        List<PostResponseDTO> posts
) {
}
