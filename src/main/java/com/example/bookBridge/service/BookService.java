package com.example.bookBridge.service;

import java.util.List;

import com.example.bookBridge.repo.ExchangeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookBridge.model.Book;
import com.example.bookBridge.model.User;
import com.example.bookBridge.repo.BookRepository;
import com.example.bookBridge.repo.UserRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ExchangeRequestRepository exchangeRequestRepository;

    public String addBook(Book book, int userId) {
        try {
            User user = userRepo.findById(userId).orElseThrow();

            book.setOwner(user);
            book.setStatus("AVAILABLE");

            Book savedBook = bookRepo.save(book);

            return "BookID " + savedBook.getId() +
                    " is added under UserID " + user.getId();

        } catch (Exception e) {
            throw new IllegalStateException("Error adding book: " + e.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public List<Book> search(String keyword) {
        return bookRepo.searchBooks(keyword);
    }

    public String deleteBook(int id) {
        try {
            Book book = bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book with ID " + id + " not found"));

            exchangeRequestRepository.deleteAll(exchangeRequestRepository.findByBookId(id));

            bookRepo.delete(book);

            return "BookID " + id + " deleted successfully";

        } catch (Exception e) {
            throw new IllegalStateException("Error deleting book: " + e.getMessage());
        }
    }
}