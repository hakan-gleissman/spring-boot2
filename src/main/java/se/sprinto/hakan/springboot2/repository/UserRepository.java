package se.sprinto.hakan.springboot2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import se.sprinto.hakan.springboot2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

