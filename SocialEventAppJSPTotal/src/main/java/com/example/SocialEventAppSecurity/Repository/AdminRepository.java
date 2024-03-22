package com.example.SocialEventAppSecurity.Repository;

import com.example.SocialEventAppSecurity.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String username);

    boolean existsByUsername(String username);
}
