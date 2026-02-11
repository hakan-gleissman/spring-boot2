package se.sprinto.hakan.springboot2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.sprinto.hakan.springboot2.dto.UserResponseDto;
import se.sprinto.hakan.springboot2.dto.UserResponseDtoBuilder;
import se.sprinto.hakan.springboot2.mapper.UserMapper;
import se.sprinto.hakan.springboot2.model.User;
import se.sprinto.hakan.springboot2.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testGetAllUsers() {
        //Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("Håkan");

        User user2 = new User();
        user.setId(2L);
        user.setUsername("Sven");

        List<User> users = new ArrayList<>();

        UserResponseDto dto1 = UserResponseDtoBuilder.builder()
                .withId(1L)
                .withUsername("Håkan")
                .build();
        UserResponseDto dto2 = UserResponseDtoBuilder.builder()
                .withId(2L)
                .withUsername("Sven")
                .build();

        when(userRepository.findAll()).thenReturn(users);
        //when(userMapper.toDto(user)).thenReturn(dto1);
        //when(userMapper.toDto(user2)).thenReturn(dto2);

        //Act
        //List<UserResponseDto> result = userService.getAllUsers();

        //Assert
        //assertEquals(2, result.size());
        assertThrows(RuntimeException.class, () -> userService.getAllUsers());


    }


}
