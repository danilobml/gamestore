package com.danilobml.gamestore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danilobml.gamestore.dto.GameListDTO;
import com.danilobml.gamestore.entities.GameList;
import com.danilobml.gamestore.projections.GameMinProjection;
import com.danilobml.gamestore.repositories.GameListRepository;
import com.danilobml.gamestore.repositories.GameRepository;
import com.danilobml.gamestore.services.interfaces.GameListService;

@Service
public class GameListServiceImpl implements GameListService {

    @Autowired
    GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {

        List<GameList> gameLists = gameListRepository.findAll();
        return gameLists.stream()
                .map(gameList -> new GameListDTO(gameList))
                .toList();

    }

    @Transactional
    public void move(Long listId, int originIndex, int destinationIndex) {

        List<GameMinProjection> list = gameRepository.searchByList(listId);
        GameMinProjection game = list.remove(originIndex);
        list.add(destinationIndex, game);

        int min = originIndex < destinationIndex ? originIndex : destinationIndex;
        int max = originIndex > destinationIndex ? originIndex : destinationIndex;

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }

    }

}
