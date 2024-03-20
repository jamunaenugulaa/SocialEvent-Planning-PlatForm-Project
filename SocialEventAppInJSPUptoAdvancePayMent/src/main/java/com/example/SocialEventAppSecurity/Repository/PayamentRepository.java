package com.example.SocialEventAppSecurity.Repository;

import com.example.SocialEventAppSecurity.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayamentRepository extends JpaRepository<Payment,Integer> {
}
