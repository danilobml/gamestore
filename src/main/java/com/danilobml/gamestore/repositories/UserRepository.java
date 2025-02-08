package com.danilobml.gamestore.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danilobml.gamestore.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
