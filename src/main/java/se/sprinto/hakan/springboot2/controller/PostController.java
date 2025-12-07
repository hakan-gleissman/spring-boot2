package se.sprinto.hakan.springboot2.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sprinto.hakan.springboot2.dto.PostRequestDTO;
import se.sprinto.hakan.springboot2.dto.PostResponseDTO;
import se.sprinto.hakan.springboot2.model.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private List<Post> posts = new ArrayList<>();

    // GET: Hämta alla inlägg
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getPosts() {

        List<PostResponseDTO> response = new ArrayList<>();

        for (Post post : posts) {
            response.add(new PostResponseDTO(
                    0L,                     // id ignoreras
                    post.getText(),
                    post.getCreatedAt()
            ));
        }

        return ResponseEntity.ok(response);
    }

    // POST: Skapa nytt inlägg
    @PostMapping
    public ResponseEntity<PostResponseDTO> addPost(@Valid @RequestBody PostRequestDTO request) {

        Post post = new Post(0L, request.text(), LocalDateTime.now());
        posts.add(post);

        PostResponseDTO response = new PostResponseDTO(
                0L,                     // id ignoreras
                post.getText(),
                post.getCreatedAt()
        );

        //TestDTO testDTO = new TestDTO("title", 2021);


        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET: Hämta ett inlägg via index
    @GetMapping("/{index}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable int index) {

        if (index < 0 || index >= posts.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Post post = posts.get(index);

        PostResponseDTO response = new PostResponseDTO(
                0L,                    // id ignoreras
                post.getText(),
                post.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }

    // PUT: Uppdatera ett inlägg
    @PutMapping("/{index}")
    public ResponseEntity<PostResponseDTO> updatePost(
            @PathVariable int index,
            @Valid @RequestBody PostRequestDTO request) {

        if (index < 0 || index >= posts.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Post post = posts.get(index);
        post.setText(request.text());

        PostResponseDTO response = new PostResponseDTO(
                0L,                     // id ignoreras
                post.getText(),
                post.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }

    // DELETE: Ta bort ett inlägg
    @DeleteMapping("/{index}")
    public ResponseEntity<Void> deletePost(@PathVariable int index) {

        if (index < 0 || index >= posts.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        posts.remove(index);
        return ResponseEntity.noContent().build();
    }
}
