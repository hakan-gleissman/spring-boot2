package se.sprinto.hakan.springboot2.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getPosts() {
        // This method seems to be incomplete in the provided snippet.
        return ResponseEntity.ok(postService.getPosts());
    }

    // GET: Hämta alla inlägg

    public ResponseEntity<Page<PostResponseDTO>> getPosts(
            @PageableDefault(
                    size = 3,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable) {

        Page<Post> posts = postService.getAllPosts(pageable);

        Page<PostResponseDTO> response = posts.map(post ->
                new PostResponseDTO(
                        post.getId(),
                        post.getText(),
                        post.getCreatedAt()
                )
        );

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
