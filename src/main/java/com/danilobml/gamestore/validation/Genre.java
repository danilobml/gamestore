package com.danilobml.gamestore.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenreValidator.class)
public @interface Genre {
    
    String message() default "The genre must be in: ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
