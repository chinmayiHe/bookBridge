package com.example.bookBridge.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class ExchangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String status; // PENDING, APPROVED, REJECTED

    @ManyToOne
    private User requester;

    @ManyToOne
    private Book book;
}