package com.example.SocialEventAppSecurity.Validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotBlankIntValidator implements ConstraintValidator<NotBlankInt, Integer> {

    @Override
    public void initialize(NotBlankInt constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && value != 0;
    }
}

