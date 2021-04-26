package com.example.restservice.repository;

import com.example.restservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudUserRepository extends JpaRepository<User, Integer> {
}
