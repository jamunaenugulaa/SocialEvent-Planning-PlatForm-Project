package com.example.SocialEventApp.Repository;

import com.example.SocialEventApp.Entity.EventOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<EventOrganizer,Integer> {
}
