package se.sprinto.hakan.springboot2.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.sprinto.hakan.springboot2.dto.UserRequestDto;
import se.sprinto.hakan.springboot2.dto.UserResponseDto;
import se.sprinto.hakan.springboot2.model.User;
import se.sprinto.hakan.springboot2.repository.UserRepository;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest_bak {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        User user = new User();
        user.setBio("bio");
        user.setUsername("jansson");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRole("ADMIN");
        user.setDisplayName("display name");
        user.setEmail("hakan@gmail.com");
        userRepository.save(user);
    }


    @Test
    void shouldGetAllUsersWithHttpBasic() throws Exception {
        // Act + Assert
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/users")
                                .with(httpBasic("jansson", "password")))
                .andExpect(status().isOk()).andReturn();

        String responseBody = result.getResponse().getContentAsString();
        List<UserResponseDto> users = objectMapper.readValue(
                responseBody, new TypeReference<List<UserResponseDto>>() {
                });
        // Verifiera att användaren är borttagen (Assert)
        Assertions.assertEquals(1, users.size());
    }


    @Test
    void shouldCreateUser() throws Exception {
        // 1. Skapa DTO (utan id)
        UserRequestDto dto = new UserRequestDto(
                "hakan",
                "hakan@test.se",
                "password",
                "USER",
                "Håkan",
                "Java-lärare",
                "/images/hakan.png"
        );

        // 2. Konvertera DTO till JSON
        String jsonData = objectMapper.writeValueAsString(dto);

        // 3. Skicka POST-request till API:et
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))

                .andExpect(status().isCreated());

        // 4. Verifiera att databasen innehåller en User
        List<User> users = userRepository.findAll();
        Assertions.assertEquals(2, users.size());
        //Assertions.assertEquals("hakan", users.get(0).getUsername());
    }

}

