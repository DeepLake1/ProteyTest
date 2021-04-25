package com.example.restservice.web;

import com.example.restservice.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public User greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new User(counter.incrementAndGet(), String.format(template, name));
    }
    @PostMapping("/postrequest")
    public User postRequest(@RequestBody User user){
        return new User(user.getId(),user.getName());
    }
}

