package com.example.SocialEventAppSecurity.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int userId;
    private String name;

    private int age;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String role;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BookingEvent> bookingEvents=new ArrayList<>();
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

    public List<BookingEvent> getBookingEvents() {
        return bookingEvents;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setBookingEvents(List<BookingEvent> bookingEvents) {
        this.bookingEvents = bookingEvents;
    }
}
