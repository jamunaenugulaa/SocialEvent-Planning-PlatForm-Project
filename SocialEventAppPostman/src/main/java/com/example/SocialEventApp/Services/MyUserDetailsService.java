package com.example.SocialEventApp.Services;

import com.example.SocialEventApp.Entity.UsersTable;
import com.example.SocialEventApp.Model.UserPrinciple;
import com.example.SocialEventApp.Repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private usersRepository repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersTable user= repo.findByUsername(username);
        if(user==null){
            System.out.println("User 404");
            throw  new UsernameNotFoundException("User 404");
        }
        return new UserPrinciple(user);
    }
}
