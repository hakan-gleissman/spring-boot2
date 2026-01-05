package se.sprinto.hakan.springboot2.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sprinto.hakan.springboot2.dto.PostRequestDTO;
import se.sprinto.hakan.springboot2.dto.PostResponseDTO;
import se.sprinto.hakan.springboot2.model.Post;
import se.sprinto.hakan.springboot2.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // GET: Hämta alla inlägg
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getPosts() {

        List<Post> posts = postService.getAllPosts();

        List<PostResponseDTO> response = posts.stream()
                .map(post -> new PostResponseDTO(
                        post.getId(),
                        post.getText(),
                        post.getCreatedAt()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }


    // GET: Hämta ett inlägg via id
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long id) {

        Post post = postService.getPost(id);

        PostResponseDTO response = new PostResponseDTO(
                post.getId(),
                post.getText(),
                post.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }

    // PUT: Uppdatera ett inlägg
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequestDTO request) {

        Post updated = postService.updatePost(id, request.text());

        PostResponseDTO response = new PostResponseDTO(
                updated.getId(),
                updated.getText(),
                updated.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }

    // DELETE: Ta bort ett inlägg
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {

        postService.deletePost(id);

        return ResponseEntity.noContent().build();
    }
}
