package com.danilobml.gamestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.danilobml.gamestore.dto.UserCreateDTO;
import com.danilobml.gamestore.entities.User;
import com.danilobml.gamestore.services.interfaces.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable Long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        User newUser = new User(userCreateDTO.getUsername(), userCreateDTO.getPassword());
        userService.saveUser(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
