package com.example.bookBridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.bookBridge.model.ExchangeRequest;
import com.example.bookBridge.service.ExchangeService;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService service;

    @PostMapping("/request")
    public String requestBook(@RequestParam int bookId,
                                       @RequestParam int userId) {
        return service.requestBook(bookId, userId);
    }

    @DeleteMapping("/{id}")
    public String deleteRequest(@PathVariable int id) {
        service.deleteRequest(id);
        return "Request deleted";
    }

    @PutMapping("/approve/{id}")
    public String approve(@PathVariable int id) {
        return service.approveRequest(id);
    }

    @PutMapping("/reject/{id}")
    public String reject(@PathVariable int id) {
        return service.rejectRequest(id);
    }
}