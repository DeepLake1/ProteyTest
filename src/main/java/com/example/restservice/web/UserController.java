package com.example.restservice.web;

import com.example.restservice.model.StatusType;
import com.example.restservice.model.User;
import com.example.restservice.repository.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/rest/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    final CrudUserRepository crudUserRepository;

    public UserController(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }


    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public int save(@Valid @RequestBody User user) {
        return crudUserRepository.save(user).getId();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return crudUserRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("User with id: " + id + " not found"));
    }
    @PostMapping("/{id}")
    public User updateUserAndStatus(@PathVariable int id, @RequestParam("status") StatusType statusType ) {
        return crudUserRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("User with id: " + id + " not found"));
    }

/*
    @Scheduled(fixedRate = 1500)
    public void testScheduled() {
        System.out.println("WTFFFFF" + System.currentTimeMillis());
    }*/

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        crudUserRepository.deleteById(id);
    }
}

