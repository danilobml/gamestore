package com.danilobml.gamestore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danilobml.gamestore.dto.GameDTO;
import com.danilobml.gamestore.dto.GameMinDTO;
import com.danilobml.gamestore.services.interfaces.GameService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService gameService;
    
    @GetMapping
    public List<GameMinDTO> findAll() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public GameDTO findById(@PathVariable long id) {
        return gameService.findById(id);
    }

}
