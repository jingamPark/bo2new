package com.example.bo2.repository;

import com.example.bo2.entity.SecUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SecUserRepository extends JpaRepository<SecUser,Long> {


    Optional<SecUser> findByUsername (String username);

    @Query ("select i from SecUser i where i.email = :email ")
    Optional<SecUser> findByEmail (String email);








}
