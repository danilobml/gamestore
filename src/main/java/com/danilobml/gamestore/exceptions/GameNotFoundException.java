package com.danilobml.gamestore.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Long id) {
        super("The game with id " + id + " was not found");
    }
}
