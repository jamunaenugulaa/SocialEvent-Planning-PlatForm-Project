package com.example.SocialEventApp.Validation;


import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FutureDateValidator.class)
@ReportAsSingleViolation
public @interface FutureDate {
    String message() default "Date must be at least 5 days in the future.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}



