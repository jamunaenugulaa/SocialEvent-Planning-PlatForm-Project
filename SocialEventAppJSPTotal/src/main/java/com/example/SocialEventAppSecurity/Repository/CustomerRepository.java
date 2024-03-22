package com.example.SocialEventAppSecurity.Repository;



 import com.example.SocialEventAppSecurity.Entity.Customer;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByName(String name);
    Customer findByUsernameAndPassword(String username,String Password);
    Customer findByUsername(String username);

    Customer findByPhone(String phone);
    boolean existsByUsername(String  username);
}
