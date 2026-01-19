package se.sprinto.hakan.springboot2.repository;


import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.sprinto.hakan.springboot2.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameOrEmail(String username, String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.posts WHERE u.id = :id")
    @OrderBy("createdAt DESC")
    Optional<User> findUserWithPosts(Long id);

    Optional<User> findByUsername(String username);
}

