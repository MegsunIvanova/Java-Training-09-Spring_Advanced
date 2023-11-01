package com.example.pathfinder.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileValidator.class)
public @interface FileAnnotation {
    long size() default 5 * 1024 * 1024;
    String[] contentTypes();

    String message() default "File is not valid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
