package com.example.SocialEventAppSecurity.Security;

import com.example.SocialEventAppSecurity.Entity.Admin;
import com.example.SocialEventAppSecurity.Entity.Customer;
import com.example.SocialEventAppSecurity.Entity.EventOrganizer;
import com.example.SocialEventAppSecurity.Repository.AdminRepository;
import com.example.SocialEventAppSecurity.Repository.CustomerRepository;
import com.example.SocialEventAppSecurity.Repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoDetailsService implements UserDetailsService {
    @Autowired
    OrganizerRepository organizerRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (organizerRepository.existsByUsername(username)) {
            Optional<EventOrganizer> mediator = organizerRepository.findAll().stream().filter(user -> user.getUsername().equals(username)).findFirst();
            return mediator.map(OrganizerInfo::new).orElseThrow(() -> new UsernameNotFoundException("Organizer not found " + username));
        }
        if (adminRepository.existsByUsername(username)) {
            Optional<Admin> mediator = adminRepository.findAll().stream().filter(user -> user.getUsername().equals(username)).findFirst();
            return mediator.map(AdminInfo::new).orElseThrow(() -> new UsernameNotFoundException("Admin not found " + username));
        }

            Optional<Customer> seller = customerRepository.findAll().stream().filter(user -> user.getUsername().equals(username)).findFirst();
            return seller.map(CustomerInfo::new).orElseThrow(() -> new UsernameNotFoundException("Customer not found " + username));


    }
}
