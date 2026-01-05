package se.sprinto.hakan.springboot2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String hello() {
        return "Hello from Spring Boot updated 3!";
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody String message) {
        System.out.println("post method");
        return ResponseEntity.<String>ok("OK");
    }


}
