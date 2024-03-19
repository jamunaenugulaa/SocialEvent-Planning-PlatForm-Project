package com.example.SocialEventAppSecurity.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

class FutureDateValidator implements ConstraintValidator<FutureDate, LocalDate> {
    private static final int MIN_DAYS_IN_FUTURE = 2;

    @Override
    public void initialize(FutureDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null) {
            return false; // Null values are considered invalid
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(MIN_DAYS_IN_FUTURE);

        return localDate.isAfter(futureDate); // Check if the date is at least 5 days in the future
    }
}
