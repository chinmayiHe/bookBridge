package com.example.bookBridge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bookBridge.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}