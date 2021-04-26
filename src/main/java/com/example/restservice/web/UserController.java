package com.example.restservice.web;

import com.example.restservice.model.User;
import com.example.restservice.repository.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/rest/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    final CrudUserRepository crudUserRepository;

    public UserController(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }


    @PostMapping("/save")
    public int save(@RequestBody User user) {
        return crudUserRepository.save(user).getId();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return crudUserRepository.findById(id).orElseThrow();
    }
}

