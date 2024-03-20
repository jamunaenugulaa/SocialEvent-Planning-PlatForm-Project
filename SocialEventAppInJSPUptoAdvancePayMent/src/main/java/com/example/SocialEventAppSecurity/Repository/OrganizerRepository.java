package com.example.SocialEventAppSecurity.Repository;


import com.example.SocialEventAppSecurity.Entity.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<EventOrganizer,Integer> {
    EventOrganizer findByUsernameAndPassword(String username, String password);
    EventOrganizer findByUsername(String username);

    boolean existsByUsername(String  username);
}
