package com.ai.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.entity.Ticket;


public interface TicketRepo extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findById(Long id);

    Optional<Ticket> findByEmail(String email);
}
