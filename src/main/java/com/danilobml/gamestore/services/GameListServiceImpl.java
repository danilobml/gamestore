package com.danilobml.gamestore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danilobml.gamestore.dto.GameListDTO;
import com.danilobml.gamestore.entities.GameList;
import com.danilobml.gamestore.exceptions.GameAlreadyExistsInListException;
import com.danilobml.gamestore.exceptions.GameNotFoundException;
import com.danilobml.gamestore.exceptions.GameNotFoundInListException;
import com.danilobml.gamestore.exceptions.ListNotFoundException;
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

    @Transactional(readOnly = true)
    public GameListDTO findById(Long id) {
        GameList gameList = gameListRepository.findById(id)
            .orElseThrow(() -> new ListNotFoundException(id));
        return new GameListDTO(gameList);
    } 

    @Transactional
    public void createList(String name) {
        gameListRepository.save(new GameList(name));
    }

    @Transactional
    public void deleteList(Long id) {
        GameList gameList = gameListRepository.findById(id)
            .orElseThrow(() -> new ListNotFoundException(id));
        List<GameMinProjection> gamesInList = gameRepository.searchByList(id);
        for (GameMinProjection game : gamesInList) {
            gameListRepository.removeGameFromList(id, game.getId());
        }
        gameListRepository.delete(gameList);
    }

    @Transactional
    public void changeGamePositionInList(Long listId, int originIndex, int destinationIndex) {

        List<GameMinProjection> list = gameRepository.searchByList(listId);
        GameMinProjection game = list.remove(originIndex);
        list.add(destinationIndex, game);

        int min = originIndex < destinationIndex ? originIndex : destinationIndex;
        int max = originIndex > destinationIndex ? originIndex : destinationIndex;

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }

    }

    @Transactional
    public void addGameToList(Long listId, Long gameId) {
        checkGameAndListExist(listId, gameId);
        Optional<Integer> gamePosition = gameListRepository.findGamePosition(listId, gameId);
        if (gamePosition.isPresent()) {
            throw new GameAlreadyExistsInListException(listId, gameId);
        }
        gameListRepository.addGameToList(listId, gameId);
    }

    @Transactional
    public void removeGameFromList(Long listId, Long gameId) {
        checkGameAndListExist(listId, gameId);

        Integer position = gameListRepository.findGamePosition(listId, gameId)
            .orElseThrow(() -> new GameNotFoundInListException(listId, gameId));

        gameListRepository.removeGameFromList(listId, gameId);
    
        Integer maxPosition = gameListRepository.findMaxPositionInList(listId).orElse(0);
    
        if (position < maxPosition) {
            gameListRepository.shiftPositionsAfterRemoval(listId, gameId);
        }
    
    }

    /**
     * Name of method: checkGameAndListExist
     * @param listId (Long)
     * @param gameId (Long)
     * @return (void)
     * @Action Checks if both game and list exist in their respective DB tables by means of throwing NotFoundExceptions if either doesn't exist.
    */
    private void checkGameAndListExist(Long listId, Long gameId) {
        gameListRepository.findById(listId)
            .orElseThrow(() -> new ListNotFoundException(listId));
        gameRepository.findById(gameId)
            .orElseThrow(() -> new GameNotFoundException(gameId));
    }

}
