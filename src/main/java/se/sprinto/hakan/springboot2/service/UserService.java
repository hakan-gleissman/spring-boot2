package se.sprinto.hakan.springboot2.service;

import org.springframework.stereotype.Service;
import se.sprinto.hakan.springboot2.dto.UserRequestDto;
import se.sprinto.hakan.springboot2.dto.UserResponseDto;
import se.sprinto.hakan.springboot2.model.User;
import se.sprinto.hakan.springboot2.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public UserResponseDto create(UserRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setDisplayName(dto.getDisplayName());
        user.setProfileImagePath(dto.getProfileImagePath());
        user.setBio(dto.getBio());

        User saved = repo.save(user);
        return UserResponseDto.fromEntity(saved);
    }

    public UserResponseDto getById(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserResponseDto.fromEntity(user);
    }

    public List<UserResponseDto> getAll() {
        return repo.findAll()
                .stream()
                .map(UserResponseDto::fromEntity)
                .toList();
    }

    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setDisplayName(dto.getDisplayName());
        user.setProfileImagePath(dto.getProfileImagePath());
        user.setBio(dto.getBio());

        User saved = repo.save(user);
        return UserResponseDto.fromEntity(saved);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}

