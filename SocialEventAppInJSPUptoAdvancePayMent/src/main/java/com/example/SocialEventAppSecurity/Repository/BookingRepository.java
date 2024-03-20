package com.example.SocialEventAppSecurity.Repository;

import com.example.SocialEventAppSecurity.Entity.BookingEvent;
import com.example.SocialEventAppSecurity.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEvent,Integer> {
    List<BookingEvent> findByCustomer(Customer customer);
}
