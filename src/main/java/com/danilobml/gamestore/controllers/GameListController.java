package com.danilobml.gamestore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danilobml.gamestore.dto.GameListCreateDTO;
import com.danilobml.gamestore.dto.GameListDTO;
import com.danilobml.gamestore.dto.GameMinDTO;
import com.danilobml.gamestore.dto.ListMoveDTO;
import com.danilobml.gamestore.services.interfaces.GameListService;
import com.danilobml.gamestore.services.interfaces.GameService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<List<GameListDTO>> findAll() {
        List<GameListDTO> gamesList = gameListService.findAll();
        return new ResponseEntity<>(gamesList, HttpStatus.OK);
    }

    @GetMapping("/{listId}/games")
    public ResponseEntity<List<GameMinDTO>> findAll(@PathVariable long listId) {
        List<GameMinDTO> gameList = gameService.searchByList(listId);
        return new ResponseEntity<>(gameList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createList(@Valid @RequestBody GameListCreateDTO gameListCreateDTO) {
        gameListService.createList(gameListCreateDTO.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteList(@PathVariable Long id) {
        gameListService.deleteList(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{listId}/move")
    public ResponseEntity<HttpStatus> moveGameInList(@PathVariable long listId, @RequestBody ListMoveDTO listMoveDTO) {
        gameListService.changeGamePositionInList(listId, listMoveDTO.getOriginIndex(), listMoveDTO.getDestinationIndex());     
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{listId}/game/{gameId}")
    public ResponseEntity<HttpStatus> addGameToList(@PathVariable long listId, @PathVariable long gameId) {
        gameListService.addGameToList(listId, gameId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{listId}/game/{gameId}")
    public ResponseEntity<HttpStatus> removeGameFromList(@PathVariable long listId, @PathVariable long gameId) {
        gameListService.removeGameFromList(listId, gameId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
