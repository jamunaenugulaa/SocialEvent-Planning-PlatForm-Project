package com.example.SocialEventApp.Repository;

import com.example.SocialEventApp.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    //Customer findByName(String name);
    Customer findByPhone(String phone);
}
