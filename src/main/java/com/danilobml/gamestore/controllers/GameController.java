package com.danilobml.gamestore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danilobml.gamestore.dto.GameMinDTO;
import com.danilobml.gamestore.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService gameService;
    
    @GetMapping
    public List<GameMinDTO> findAll() {
        return gameService.fidAll();
    }

}
