package com.danilobml.gamestore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danilobml.gamestore.dto.GameListDTO;
import com.danilobml.gamestore.entities.GameList;
import com.danilobml.gamestore.repositories.GameListRepository;
import com.danilobml.gamestore.services.interfaces.GameListService;


@Service
public class GameListServiceImpl implements GameListService{
    
    @Autowired
    GameListRepository gameListRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> gameLists = gameListRepository.findAll();
        return gameLists.stream()
            .map(gameList -> new GameListDTO(gameList))
            .toList();
    }

}
