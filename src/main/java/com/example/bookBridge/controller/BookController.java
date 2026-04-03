package com.example.bookBridge.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.bookBridge.model.Book;
import com.example.bookBridge.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping("/{userId}")
    public String addBook(@RequestBody Book book, @PathVariable int userId) {
        return service.addBook(book, userId);
    }

    @GetMapping
    public List<Book> getAll() {
        return service.getAllBooks();
    }

    @GetMapping("/search")
    public List<Book> search(@RequestParam String keyword) {
        return service.search(keyword);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        return service.deleteBook(id);





    }
}