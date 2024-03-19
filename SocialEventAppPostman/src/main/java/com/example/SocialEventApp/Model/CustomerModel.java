package com.example.SocialEventApp.Model;

import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

@Component
public class CustomerModel {
    private int userId;
    @NotBlank(message = "Name cannot be blank")
    @Size(min=3,message = "Name should have atleast 3 characters")
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 150, message = "Age must be less than 150")
    private int age;
    @Positive(message = "Phone number must be positive")
    @Pattern(regexp = "^[7-9][0-9]{9}$", message = "Invalid phone number format")
    private String phone;


    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    public CustomerModel() {
    }

    public CustomerModel(int userId, String name, int age, String phnNum, String email) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.phone = phnNum;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhnNum() {
        return phone;
    }

    public void setPhnNum(String phnNum) {
        this.phone = phnNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
