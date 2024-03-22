package com.example.SocialEventAppSecurity.Validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotBlankIntValidator.class)
public @interface NotBlankInt {
    String message() default "Id must not be blank";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

