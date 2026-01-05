package se.sprinto.hakan.springboot2.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.sprinto.hakan.springboot2.dto.UserRequestDto;
import se.sprinto.hakan.springboot2.dto.UserResponseDto;
import se.sprinto.hakan.springboot2.dto.UserWithPostsResponseDto;
import se.sprinto.hakan.springboot2.mapper.UserMapper;
import se.sprinto.hakan.springboot2.model.User;
import se.sprinto.hakan.springboot2.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setRole(dto.role());
        user.setDisplayName(dto.displayName());
        user.setProfileImagePath(dto.profileImagePath());
        user.setBio(dto.bio());
        user.setEmail(dto.email());

        User saved = userRepository.save(user);
        return UserResponseDto.fromEntity(saved);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole(),
                        user.getDisplayName(),
                        user.getBio(),
                        user.getProfileImagePath()
                ))
                .toList();
    }

    public UserResponseDto getById(Long id) {
        Optional<User> opt = userRepository.findById(id);

        if (!opt.isPresent()) {
            throw new NoSuchElementException("User not found with id: " + id);
        }

        return userMapper.toDto(opt.get());
    }

    public UserResponseDto addUser(UserRequestDto userDto) {
        User user = userMapper.fromDto(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean exists = userRepository.existsByUsernameOrEmail(
                user.getUsername(), user.getEmail()
        );

        if (exists) {
            throw new IllegalArgumentException(
                    "User med detta username eller email finns redan i databasen"
            );
        }

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    private UserResponseDto toDto(User user) {
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

    private User fromDto(UserRequestDto userDto) {
        User user = new User();
        user.setBio(userDto.bio());
        user.setDisplayName(userDto.displayName());
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());
        user.setRole(userDto.role());
        user.setProfileImagePath(userDto.profileImagePath());
        user.setUsername(userDto.username());
        return user;
    }


    public UserWithPostsResponseDto getUserWithPosts(Long id) {
        User user = userRepository.findUserWithPosts(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id " + id));

        UserWithPostsResponseDto dto = userMapper.toWithPostsDto(user);
        return dto;

    }
}
