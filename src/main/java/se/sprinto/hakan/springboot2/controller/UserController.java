package se.sprinto.hakan.springboot2.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.sprinto.hakan.springboot2.dto.*;
import se.sprinto.hakan.springboot2.service.PostService;
import se.sprinto.hakan.springboot2.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService service;

    private final PostService postService;

    public UserController(UserService service, PostService postService) {
        this.service = service;
        this.postService = postService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<UserResponseDto> allUsers = service.getAllUsers();
        return ResponseEntity.ok().body(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        UserResponseDto userResponseDto = service.getById(id);
        return ResponseEntity.ok().body(userResponseDto);
    }

   
    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addUser(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserRequestDto dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{userId}/posts")
    public ResponseEntity<PostResponseDTO> createPostForUser(
            @PathVariable Long userId,
            @Valid @RequestBody PostRequestDTO request) {

        // Anropa service
        PostResponseDTO response = postService.createPost(userId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}/with-posts")
    public ResponseEntity<UserWithPostsResponseDto> getUserWithPosts(@PathVariable Long id) {

        UserWithPostsResponseDto response = service.getUserWithPosts(id);

        return ResponseEntity.ok(response);
    }

}

