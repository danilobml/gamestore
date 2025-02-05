package com.danilobml.gamestore.exceptions;

public class GameAlreadyExistsInListException extends RuntimeException {
    public GameAlreadyExistsInListException(Long listId, Long gameId) {
        super("The game with id " + gameId + " already exists in the list with id: " + listId);
    }

}
