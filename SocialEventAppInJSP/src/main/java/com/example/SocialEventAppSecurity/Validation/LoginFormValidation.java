package com.example.SocialEventAppSecurity.Validation;

import com.example.SocialEventAppSecurity.Model.LoginForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class LoginFormValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return LoginForm.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        LoginForm loginForm= (LoginForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "OrganizerUsername");
        if(loginForm.getPassword()!=null && loginForm.getPassword().toString().length()<5){
            errors.rejectValue("password", "OrganizerPassword", "AdminPassword");

        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "OrganizerPasswordNull");

    }

}