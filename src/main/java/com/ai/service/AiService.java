package com.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.AdvisorSpec;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.ai.tools.EmailTool;
import com.ai.tools.TicketDatabaseTools;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class AiService {
	
	private final ChatClient chatClient;
	private final TicketDatabaseTools ticketDatabaseTools;
	private final EmailTool emailTool;
	
	@Value("classpath:/prompts/system-promt.st")
	private Resource systemPromptResource;
	
	public Flux<String> getResponseFromAssistant(String query, String conversationId) {
		
		
		return this.chatClient
				.prompt()
				.advisors(advisorSpec ->
		                advisorSpec.param(ChatMemory.CONVERSATION_ID, conversationId)
		        )
				.tools(ticketDatabaseTools,emailTool)
				.system(systemPromptResource)
				.user(query)
				.stream()
				.content();
	}
	
}
