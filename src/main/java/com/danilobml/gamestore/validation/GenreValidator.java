package com.danilobml.gamestore.validation;

import java.util.List;

import jakarta.validation.ConstraintValidator;

public class GenreValidator implements ConstraintValidator<Genre, String> {

    List<String> validGenres = ValidGenres.GENRES;

    public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {
        for (String genre : validGenres) {
            if (value.equals(genre)) {
                return true;
            }
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("The genre must be one of: " + ValidGenres.getFormattedGenres())
                .addConstraintViolation();
        return false;
    };

}
