package com.danilobml.gamestore.exceptions;

public class ListNotFoundException extends RuntimeException {
    public ListNotFoundException(Long id) {
        super("The games list with id " + id + " was not found");
    }
}
