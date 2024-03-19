package com.example.SocialEventApp.Repository;

import com.example.SocialEventApp.Entity.BookingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEvent,Integer> {
}
