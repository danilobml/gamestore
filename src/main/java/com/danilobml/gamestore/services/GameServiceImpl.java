package com.danilobml.gamestore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danilobml.gamestore.dto.GameCreateDTO;
import com.danilobml.gamestore.dto.GameDTO;
import com.danilobml.gamestore.dto.GameMinDTO;
import com.danilobml.gamestore.entities.Game;
import com.danilobml.gamestore.exceptions.GameNotFoundException;
import com.danilobml.gamestore.projections.GameMinProjection;
import com.danilobml.gamestore.repositories.GameListRepository;
import com.danilobml.gamestore.repositories.GameRepository;
import com.danilobml.gamestore.services.interfaces.GameService;

@Service
public class GameServiceImpl implements GameService {
    
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameListRepository gameListRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        return result.stream()
            .map(game -> new GameMinDTO(game))
            .toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findById(long id) {
        Game game = gameRepository.findById(id)
            .orElseThrow(() -> new GameNotFoundException(id));
        return new GameDTO(game);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> searchByList(Long listId) {
        List<GameMinProjection> projectionList = gameRepository.searchByList(listId);
        return projectionList.stream()
            .map(projection -> new GameMinDTO(projection))
            .toList();
    }

    @Transactional
    public GameDTO createGame(GameCreateDTO gameCreateDTO) {
        Game newGame = gameRepository.save(new Game(gameCreateDTO));
        return new GameDTO(newGame); 
    }

    @Transactional
    public GameDTO updateGame(Long id, GameDTO gameDTO) {
        Game game = gameRepository.findById(id)
            .orElseThrow(() -> new GameNotFoundException(id));
        
        game.setTitle(gameDTO.getTitle());
        game.setGenre(gameDTO.getGenre());
        game.setPlatforms(gameDTO.getPlatforms());
        game.setScore(gameDTO.getScore());
        game.setImgUrl(gameDTO.getImgUrl());
        game.setYear(gameDTO.getYear());
        game.setShortDescription(gameDTO.getShortDescription());
        game.setLongDescription(gameDTO.getLongDescription());

        gameRepository.save(game);
        return new GameDTO(game);
    }
    
    @Transactional
    public void deleteGame(Long id) {
        Game game = gameRepository.findById(id)
            .orElseThrow(() -> new GameNotFoundException(id));
        List<Long> listIds = gameRepository.findListIdsByGameId(id);
        for (Long listId : listIds) {
            gameListRepository.removeGameFromList(listId, id);
        }
        gameRepository.delete(game);
    }

}
