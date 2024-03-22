package com.example.SocialEventAppSecurity.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class AdminModel {
    private int adminId;
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name should have at least 3 characters")
    private String adminName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    private String role;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;

    public AdminModel() {
    }

    public AdminModel(int adminId, String adminName, String email, String role, String username, String password) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.email = email;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
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

    @Override
    public String toString() {
        return "AdminModel{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
