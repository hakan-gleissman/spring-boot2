package se.sprinto.hakan.springboot2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.sprinto.hakan.springboot2.dto.UserRequestDto;
import se.sprinto.hakan.springboot2.dto.UserResponseDto;
import se.sprinto.hakan.springboot2.mapper.UserMapper;
import se.sprinto.hakan.springboot2.model.User;
import se.sprinto.hakan.springboot2.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testAddUser() {
        // Arrange
        UserRequestDto dto = new UserRequestDto(
                "hakan",
                "hakan@test.se",
                "password",
                "ADMIN",
                "Håkan",
                "Bio text",
                "image.png"
        );

        User mappedUser = new User();
        mappedUser.setUsername("hakan");
        mappedUser.setEmail("hakan@test.se");
        mappedUser.setPassword("password");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("hakan");
        savedUser.setEmail("hakan@test.se");

        UserResponseDto responseDto = new UserResponseDto(
                1L,
                "hakan",
                "hakan@test.se",
                "ADMIN",
                "Håkan",
                "Bio text",
                "image.png"
        );

        //when(userMapper.fromDto(dto)).thenReturn(mappedUser);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.existsByUsernameOrEmail("hakan", "hakan@test.se"))
                .thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        //when(userMapper.toDto(savedUser)).thenReturn(responseDto);

        // Act
        UserResponseDto result = userService.addUser(dto);

        // Assert
        assertEquals("hakan", result.username());
        assertEquals("hakan@test.se", result.email());
        assertEquals(1L, result.id());
    }


}
