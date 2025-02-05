package com.danilobml.gamestore.validation;

import java.util.List;
import java.util.stream.Collectors;

public class ValidGenres {
 
    public static final List<String> GENRES = List.of( "Role-playing (RPG), Shooter", "Role-playing (RPG), Adventure", "Platform" );

    public static String getFormattedGenres() {
        return GENRES.stream()
                     .map(genre -> "\"" + genre + "\"")
                     .collect(Collectors.joining(", "));
    }

}
