package se.sprinto.hakan.springboot2.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.sprinto.hakan.springboot2.dto.UserResponseDto;
import se.sprinto.hakan.springboot2.model.User;
import se.sprinto.hakan.springboot2.repository.UserRepository;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        User user = new User();
        user.setRole("ADMIN");
        user.setPassword(passwordEncoder.encode("password"));
        user.setEmail("admin@gmail.com");
        user.setDisplayName("admin display");
        user.setBio("my bio");
        user.setUsername("admin");
        userRepository.save(user);
    }

    @Test
    void shouldGetAllUsers() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .with(httpBasic("admin", "password")))
                .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        List<UserResponseDto> users = objectMapper.readValue(
                response, new TypeReference<List<UserResponseDto>>() {
                });
        assertEquals(1, users.size());
    }
}
