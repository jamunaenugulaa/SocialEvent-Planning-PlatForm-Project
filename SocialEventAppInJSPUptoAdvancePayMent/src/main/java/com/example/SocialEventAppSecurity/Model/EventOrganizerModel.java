package com.example.SocialEventAppSecurity.Model;

import com.example.SocialEventAppSecurity.Validation.NotBlankInt;
import jakarta.validation.constraints.*;

public class EventOrganizerModel {
    @Min(value=1000,message = "Id Can Be 4 Digit Number")
    @Max(value=10000,message = "Cant Exceed 4 Digits")
    @NotBlankInt
    private int id;
    @NotBlank(message = "Organizer Name Should Not Be Empty")
    @Size(min=3,message = "Name should have least 3 characters")
    private String name;
    @Min(value = 20, message = "Age should be at least 20")
    private int age;

    @Pattern(regexp = "^[7-9][0-9]{9}$", message = "Invalid phone number format")
    private String phnNum;
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    private String role;
    private String username;

    private String password;



    public EventOrganizerModel() {
    }

    public EventOrganizerModel(int id, String name, int age, String phnNum, String email, String role) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phnNum = phnNum;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return phnNum;
    }

    public void setPhnNum(String phnNum) {
        this.phnNum = phnNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


