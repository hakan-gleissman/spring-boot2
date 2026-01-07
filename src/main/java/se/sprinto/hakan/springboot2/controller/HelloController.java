package se.sprinto.hakan.springboot2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sprinto.hakan.springboot2.util.AppLogger;

@RestController
@RequestMapping("/hello")
public class HelloController {
    private final AppLogger logger;

    public HelloController(AppLogger logger) {
        this.logger = logger;
    }

    @GetMapping
    public String hello() {
        logger.info("Hello from Spring Boot!");
        logger.info("Executing {} {}", HelloController.class.getSimpleName(), "hello");
        return "Hello from Spring Boot updated 3!";
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody String message) {
        System.out.println("post method");
        return ResponseEntity.<String>ok("OK");
    }


}
