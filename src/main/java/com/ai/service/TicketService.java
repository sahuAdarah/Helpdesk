package com.ai.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.ai.dao.TicketRepo;
import com.ai.entity.Ticket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Service
@Getter
@Setter
@RequiredArgsConstructor
public class TicketService {

	private final TicketRepo ticketRepo;
	
	
	public Ticket createTicket(Ticket ticket) {
		ticket.setId(null);
		return ticketRepo.save(ticket);
	}
	

	public Ticket getTicket(Long ticketId) {
		var ticket =ticketRepo.findById(ticketId).orElse(null);
		return ticket;
		
	}
	
	public Ticket getTicketByEmailId(String emailId) {
		return ticketRepo.findByEmail(emailId).orElse(null);
		
	}
	
	public Ticket updateTicket(Ticket ticket) {
		return ticketRepo.save(ticket);
		
	}
	
	


	
	
}
