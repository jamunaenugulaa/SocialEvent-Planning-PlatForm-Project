package com.example.SocialEventAppSecurity.Repository;


import com.example.SocialEventAppSecurity.Entity.EventOrganizer;
import com.example.SocialEventAppSecurity.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer> {
    List<Location> findByEventOrganizer(EventOrganizer eventOrganizer);
}
