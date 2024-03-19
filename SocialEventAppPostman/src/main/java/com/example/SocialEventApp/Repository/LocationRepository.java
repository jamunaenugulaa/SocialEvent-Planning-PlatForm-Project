package com.example.SocialEventApp.Repository;

import com.example.SocialEventApp.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Integer> {
}
