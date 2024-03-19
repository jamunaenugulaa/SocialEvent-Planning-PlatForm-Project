package com.example.SocialEventAppSecurity.Validation;

import com.example.SocialEventAppSecurity.Model.LoginForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class CustomerValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return LoginForm.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        LoginForm employeeModel= (LoginForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "OrganizerUsername");
        if(employeeModel.getPassword()!=null && employeeModel.getPassword().toString().length()<5){
            errors.rejectValue("password", "CustomerPassword", "AdminPassword");

        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "OrganizerPasswordNull");

    }}
