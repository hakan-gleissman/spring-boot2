package se.sprinto.hakan.springboot2.service;

import org.springframework.stereotype.Service;
import se.sprinto.hakan.springboot2.dto.UserRequestDto;
import se.sprinto.hakan.springboot2.dto.UserResponseDto;
import se.sprinto.hakan.springboot2.model.User;
import se.sprinto.hakan.springboot2.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService_bak {

    private final UserRepository repo;

    public UserService_bak(UserRepository repo) {
        this.repo = repo;
    }

    public UserResponseDto create(UserRequestDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setRole(dto.role());
        user.setDisplayName(dto.displayName());
        user.setProfileImagePath(dto.profileImagePath());
        user.setBio(dto.bio());

        User saved = repo.save(user);
        return UserResponseDto.fromEntity(saved);
    }

    public UserResponseDto getById(Long id) {
        Optional<User> opt = repo.findById(id);

        if (!opt.isPresent()) {
            throw new NoSuchElementException("User not found with id: " + id);
        }

        return UserResponseDto.fromEntity(opt.get());
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

        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setRole(dto.role());
        user.setDisplayName(dto.displayName());
        user.setProfileImagePath(dto.profileImagePath());
        user.setBio(dto.bio());

        User saved = repo.save(user);
        return UserResponseDto.fromEntity(saved);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
