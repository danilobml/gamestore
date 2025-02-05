package com.danilobml.gamestore.services.interfaces;

import java.util.List;

import com.danilobml.gamestore.dto.GameListDTO;

public interface GameListService {
    List<GameListDTO> findAll();
    GameListDTO findById(Long id);
    void createList(String name);
    void deleteList(Long id);
    void changeGamePositionInList(Long listId, int sourceIndex, int destinationIndex);
    void addGameToList(Long listId, Long gameId);
    void removeGameFromList(Long listId, Long gameId);
}
