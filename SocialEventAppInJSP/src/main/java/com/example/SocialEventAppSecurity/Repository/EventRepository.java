package com.example.SocialEventAppSecurity.Repository;


import com.example.SocialEventAppSecurity.Entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Events,Integer> {
    Events findByEventName(String eventName);
}
