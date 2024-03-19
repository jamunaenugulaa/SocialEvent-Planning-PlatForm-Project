package com.example.SocialEventApp.SecurityConfig;

import com.example.SocialEventApp.Entity.UsersTable;
import com.example.SocialEventApp.Repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersTableServices {
    @Autowired
    private usersRepository repo;
    public UsersTable saveUser(UsersTable user) {
        return repo.save(user);
    }
}
