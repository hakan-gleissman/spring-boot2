package se.sprinto.hakan.springboot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sprinto.hakan.springboot2.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
