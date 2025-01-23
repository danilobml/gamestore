package com.danilobml.gamestore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danilobml.gamestore.dto.GameListDTO;
import com.danilobml.gamestore.dto.GameMinDTO;
import com.danilobml.gamestore.dto.ListMoveDTO;
import com.danilobml.gamestore.services.interfaces.GameListService;
import com.danilobml.gamestore.services.interfaces.GameService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping(value = "/lists")
public class GameListController {
    
    @Autowired
    private GameListService gameListService;

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameListDTO> findAll() {
        return gameListService.findAll();
    }

    @GetMapping("/{listId}/games")
    public List<GameMinDTO> findAll(@PathVariable long listId) {
        return gameService.searchByList(listId);
    }

    @PostMapping("/{listId}/move")
    public void moveGameInList(@PathVariable long listId, @RequestBody ListMoveDTO listMoveDTO) {
        
        gameListService.move(listId, listMoveDTO.getOriginIndex(), listMoveDTO.getDestinationIndex());
        
    }
    
}
