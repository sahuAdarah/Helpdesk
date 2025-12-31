package com.ai.tools;

import java.time.LocalDateTime;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.ai.entity.Priority;
import com.ai.entity.Status;
import com.ai.entity.Ticket;
import com.ai.service.TicketService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TicketDatabaseTools {
	
	private final TicketService ticketService;
	
	//create ticket tool
//	@Tool(description="This tool helps to crete new ticket in database.")
//	public Ticket createTicket (@ToolParam(description="Ticket fields required to create new ticket") Ticket ticket) {
//		try {
//			System.out.println("going to create ticket");
//			System.out.println(ticket);
//			return ticketService.createTicket(ticket);
//		}catch(Exception e) {
//			System.out.println("Ticket is not created");
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	@Tool(description = "This tool helps to crete new ticket in database.")
    public Ticket createTicket(
            @ToolParam(description = "User email address") String email,
            @ToolParam(description = "Short summary of the issue") String summary,
            @ToolParam(description = "Detailed description of the issue") String description,
            @ToolParam(description = "Priority: LOW, MEDIUM, HIGH, URGENT") String priority,
            @ToolParam(description = "Issue category") String category
    ) {

        // Convert String → Enum safely
        Priority ticketPriority = Priority.valueOf(priority.toUpperCase());

        Ticket ticket = new Ticket();
        ticket.setEmail(email);
        ticket.setSummary(summary);
        ticket.setDescription(description);
        ticket.setPriority(ticketPriority);
        ticket.setCategory(category);
        ticket.setStatus(Status.OPEN); // default

        // ❌ DO NOT set ID
        // ❌ DO NOT set createdOn / updatedOn (handled by @PrePersist)

        return ticketService.createTicket(ticket);
    }


	
	//get ticket using username
	@Tool(description="This tool helps to get ticket by username.")
	public Ticket getTiketByUsername(@ToolParam(description="email id whoose ticket is required.") String emailiId) {
		return ticketService.getTicketByEmailId(emailiId);
	}
	
	//update ticket by username
	@Tool(description="This tool helps to update ticket.")
	public Ticket updateTicket(@ToolParam(description="new ticket detail with thickt id.") Ticket ticket) {
		return ticketService.updateTicket(ticket);
	}
	
	//get current system time
	@Tool(description="This tool helps to get current system time.")
	public String getCurrentTime() {
		return String.valueOf(System.currentTimeMillis());
//		return LocalDateTime.now()
//                .atZone(LocaleContextHolder.getTimeZone().toZoneId())
//                .toString();
	}
	
	
}
