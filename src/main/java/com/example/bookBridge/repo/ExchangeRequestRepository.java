package com.example.bookBridge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bookBridge.model.ExchangeRequest;

public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequest, Integer> {
    Iterable<? extends ExchangeRequest> findByBookId(int bookId);

    Iterable<? extends ExchangeRequest> findByRequesterId(int userId);
}