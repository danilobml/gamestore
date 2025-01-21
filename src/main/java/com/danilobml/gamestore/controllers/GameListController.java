package com.danilobml.gamestore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danilobml.gamestore.dto.GameListDTO;
import com.danilobml.gamestore.services.interfaces.GameListService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(value = "/lists")
public class GameListController {
    
    @Autowired
    GameListService gameListService;

    @GetMapping
    public List<GameListDTO> findAll() {
        return gameListService.findAll();
    }

}
