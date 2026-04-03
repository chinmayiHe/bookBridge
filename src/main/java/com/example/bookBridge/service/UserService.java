package com.example.bookBridge.service;

import com.example.bookBridge.repo.BookRepository;
import com.example.bookBridge.repo.ExchangeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bookBridge.model.User;
import com.example.bookBridge.repo.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private ExchangeRequestRepository exchangeRequestRepository;
    @Autowired
    private BookRepository bookRepository;

    public String register(User user) {
        try {
            User savedUser = repo.save(user);
            return "UserID " + savedUser.getId() + " registered successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error registering user: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public String deleteUser(int userId) {
        try {
            exchangeRequestRepository.deleteAll(exchangeRequestRepository.findByRequesterId(userId));

            bookRepository.deleteAll(bookRepository.findByOwnerId(userId));

            repo.deleteById(userId);

            return "User deleted successfully\n" +
                    "first deletes the requests made by user\n" +
                    "secondly deletes the books owned by user\n" +
                    "at last user is deleted";

        } catch (Exception e) {
            throw new RuntimeException("Error deleting user: " + e.getMessage());
        }
    }
}