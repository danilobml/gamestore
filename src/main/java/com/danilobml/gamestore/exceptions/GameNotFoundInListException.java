package com.danilobml.gamestore.exceptions;

public class GameNotFoundInListException extends RuntimeException {
    public GameNotFoundInListException(Long listId, Long gameId) {
        super("The game with id " + gameId + " was not found in the list with id " + listId);
    }
}
