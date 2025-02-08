package com.danilobml.gamestore.controllers;

import com.danilobml.gamestore.dto.UserCreateDTO;
import com.danilobml.gamestore.entities.User;
import com.danilobml.gamestore.services.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
