package com.danilobml.gamestore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danilobml.gamestore.dto.GameMinDTO;
import com.danilobml.gamestore.entities.Game;
import com.danilobml.gamestore.repositories.GameRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> fidAll() {
        List<Game> result = gameRepository.findAll();
        return result.stream()
            .map(game -> new GameMinDTO(game))
            .toList();
    }

}
