package com.example.SocialEventAppSecurity.Repository;


import com.example.SocialEventAppSecurity.Entity.UsersTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface usersRepository extends JpaRepository<UsersTable,Integer> {
    UsersTable findByUsername(String username);
}

