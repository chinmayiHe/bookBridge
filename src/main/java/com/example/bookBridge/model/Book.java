package com.example.bookBridge.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String author;
    private String status; // AVAILABLE, REQUESTED, EXCHANGED

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}