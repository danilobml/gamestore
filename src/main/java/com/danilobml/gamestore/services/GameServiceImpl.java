package com.danilobml.gamestore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danilobml.gamestore.dto.GameDTO;
import com.danilobml.gamestore.dto.GameMinDTO;
import com.danilobml.gamestore.entities.Game;
import com.danilobml.gamestore.projections.GameMinProjection;
import com.danilobml.gamestore.repositories.GameRepository;
import com.danilobml.gamestore.services.interfaces.GameService;

@Service
public class GameServiceImpl implements GameService {
    
    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        return result.stream()
            .map(game -> new GameMinDTO(game))
            .toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findById(long id) {
        Game game = gameRepository.findById(id).orElse(null);
        return game != null ? new GameDTO(game) : null;
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> searchByList(Long listId) {
        List<GameMinProjection> projectionList = gameRepository.searchByList(listId);
        return projectionList.stream()
            .map(projection -> new GameMinDTO(projection))
            .toList();
    }

}
