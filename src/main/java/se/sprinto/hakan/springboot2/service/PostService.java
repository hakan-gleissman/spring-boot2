package se.sprinto.hakan.springboot2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.sprinto.hakan.springboot2.dto.PostRequestDTO;
import se.sprinto.hakan.springboot2.dto.PostResponseDTO;
import se.sprinto.hakan.springboot2.model.Post;
import se.sprinto.hakan.springboot2.model.User;
import se.sprinto.hakan.springboot2.repository.PostRepository;
import se.sprinto.hakan.springboot2.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // CREATE
    public PostResponseDTO createPost(Long userId, PostRequestDTO postDto) {
        // Skapa Post-objekt med tom konstruktor och setters
        Post post = new Post();
        post.setText(postDto.text());
        post.setCreatedAt(LocalDateTime.now());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id " + userId));

        post.setUser(user);
        Post fromDb = postRepository.save(post);
        return new PostResponseDTO(fromDb.getId(), fromDb.getText(), fromDb.getCreatedAt());
    }

    // READ ALL
    public Page<Post> getAllPosts(Pageable page) {
        return postRepository.findAll(page);
    }

    public List<PostResponseDTO> getPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostResponseDTO(
                        post.getId(),
                        post.getText(),
                        post.getCreatedAt()
                ))
                .toList();
    }

    // READ ONE
    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Post not found with id " + id));
    }

    // UPDATE
    public Post updatePost(Long id, String newText) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Post not found with id " + id));

        post.setText(newText);
        return postRepository.save(post);
    }

    // DELETE
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new NoSuchElementException("Post not found with id " + id);
        }
        postRepository.deleteById(id);
    }
}

