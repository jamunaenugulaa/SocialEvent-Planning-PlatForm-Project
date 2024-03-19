package com.example.SocialEventApp.Repository;

import com.example.SocialEventApp.Entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Events,Integer> {
    //Events findByCategory(String eventCategory);
}
