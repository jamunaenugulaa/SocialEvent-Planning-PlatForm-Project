package com.example.SocialEventAppSecurity.Validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FutureDateValidator.class)
@ReportAsSingleViolation
public @interface FutureDate {
    String message() default "Date must be at least 2 days in the future.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}



