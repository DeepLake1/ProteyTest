package com.example.restservice.web.controllers;

import com.example.restservice.model.Status;
import com.example.restservice.model.StatusType;
import com.example.restservice.model.User;
import com.example.restservice.repository.CrudUserRepository;
import com.example.restservice.util.StatusChangingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
        user.setStatus(new Status(StatusType.ONLINE,LocalDateTime.now()));
        return crudUserRepository.save(user).getId();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return crudUserRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("User with id: " + id + " not found"));
    }

    @PostMapping("/{id}")
    public String updateUserAndStatus(@PathVariable int id, @RequestParam("status") StatusType statusType) {
        User user = crudUserRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("User with id: " + id + " not found"));
        Status userStatus = user.getStatus();
        StatusType oldStatusType = userStatus.getStatusType();
        userStatus.setStatusType(statusType);
        userStatus.setLastTimeStatusChanged(LocalDateTime.now());
        crudUserRepository.flush();
        return "{ 'User id' : '" + user.getId() + "', 'OldStatus' : '" + oldStatusType + "', 'NewStatus' : '" + statusType + "' }";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        crudUserRepository.deleteById(id);
    }
}

