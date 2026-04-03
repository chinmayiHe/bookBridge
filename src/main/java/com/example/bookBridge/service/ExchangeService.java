package com.example.bookBridge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookBridge.model.*;
import com.example.bookBridge.repo.*;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRequestRepository requestRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private UserRepository userRepo;

    public String requestBook(int bookId, int userId) {
        try {
            Book book = bookRepo.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!book.getStatus().equals("AVAILABLE")) {
                return "Book is already requested ";
            }

            ExchangeRequest request = new ExchangeRequest();
            request.setBook(book);
            request.setRequester(user);
            request.setStatus("PENDING");

            book.setStatus("REQUESTED");
            bookRepo.save(book);

            requestRepo.save(request);

            return "BookID " + book.getId() + " is requested";

        } catch (Exception e) {
            throw new IllegalStateException("Error: " + e.getMessage());
        }
    }

    public String approveRequest(int id) {
        try {
            ExchangeRequest req = requestRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Request not found"));

            req.setStatus("APPROVED");
            req.getBook().setStatus("EXCHANGED");

            bookRepo.save(req.getBook());
            requestRepo.save(req);

            return "Request ID " + id + " approved. BookID " +
                    req.getBook().getId() + " is exchanged";

        } catch (Exception e) {
            throw new IllegalStateException("Error approving request: " + e.getMessage());
        }
    }

    public String rejectRequest(int id) {
        try {
            ExchangeRequest req = requestRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Request not found"));

            req.setStatus("REJECTED");
            req.getBook().setStatus("AVAILABLE");

            bookRepo.save(req.getBook());
            requestRepo.save(req);

            return "Request ID " + id + " rejected. BookID " +
                    req.getBook().getId() + " is available again";

        } catch (Exception e) {
            throw new IllegalStateException("Error rejecting request: " + e.getMessage());
        }
    }

    @Autowired
    private ExchangeRequestRepository repository;

    public void deleteRequest(int id) {
        repository.deleteById(id);
    }
}