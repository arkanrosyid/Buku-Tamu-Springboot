package com.bukutamu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bukutamu.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("SELECT u FROM Admin u WHERE u.email = ?1")
    Admin findByEmail(String email);

}
