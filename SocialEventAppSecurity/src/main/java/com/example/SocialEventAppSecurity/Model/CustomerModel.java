package com.example.SocialEventAppSecurity.Model;

import com.example.SocialEventAppSecurity.Entity.BookingEvent;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    @NotBlank(message = "Username is required")
    private String username;

    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;
    private String role;
    private List<BookingEvent> bookingEvents=new ArrayList<>();

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getRole() {
        return role;
    }

    public List<BookingEvent> getBookingEvents() {
        return bookingEvents;
    }

    public void setBookingEvents(List<BookingEvent> bookingEvents) {
        this.bookingEvents = bookingEvents;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
