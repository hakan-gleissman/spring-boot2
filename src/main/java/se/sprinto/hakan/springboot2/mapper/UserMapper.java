package se.sprinto.hakan.springboot2.mapper;

import org.springframework.stereotype.Component;
import se.sprinto.hakan.springboot2.dto.PostResponseDTO;
import se.sprinto.hakan.springboot2.dto.UserRequestDto;
import se.sprinto.hakan.springboot2.dto.UserResponseDto;
import se.sprinto.hakan.springboot2.dto.UserWithPostsResponseDto;
import se.sprinto.hakan.springboot2.model.User;

import java.util.List;

@Component
public class UserMapper {


    public User fromDto(UserRequestDto dto) {
        User user = new User();
        setUserValues(user, dto);
        return user;
    }

    public User fromDto(User user, UserRequestDto dto) {
        setUserValues(user, dto);
        return user;
    }

    private void setUserValues(User user, UserRequestDto dto) {
        user.setProfileImagePath(dto.profileImagePath());
        user.setRole(dto.role());
        user.setPassword(dto.password());
        user.setEmail(dto.email());
        user.setBio(dto.bio());
        user.setUsername(dto.username());
        user.setDisplayName(dto.displayName());
    }


    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto(
                user.getId(), user.getUsername(), user.getEmail(),
                user.getRole(), user.getDisplayName(), user.getBio(),
                user.getProfileImagePath());
        return dto;

    }

    public UserWithPostsResponseDto toWithPostsDto(User user) {
        List<PostResponseDTO> posts = user.getPosts()
                .stream()
                .map(p -> new PostResponseDTO(
                        p.getId(),
                        p.getText(),
                        p.getCreatedAt(),
                        toDto(p.getUser())
                ))
                .toList();

        UserResponseDto dto = new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getDisplayName(),
                user.getBio(),
                user.getProfileImagePath());

        UserWithPostsResponseDto dtoToReturn = new UserWithPostsResponseDto(
                dto, posts);
        return dtoToReturn;
    }


}
