package com.ai.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AiConfig{
	
	private Logger logger = LoggerFactory.getLogger(AiConfig.class); 
	
//	public JdbcChatMemoryRepository jdbcChatMemoryRepository() {
//		return JdbcChatMemoryRepository.builder()
//					.jdbcTemplate(null)
//					.build();
//	}
//	
	
	@Bean
	public ChatClient chatClient(ChatClient.Builder builder, JdbcChatMemoryRepository jdbcChatMemoryRepository) {
    
		//chat memmory ko create kr sakte hai
		var chatMemory = MessageWindowChatMemory.builder()
					.chatMemoryRepository(jdbcChatMemoryRepository)
					.maxMessages(15)
					.build();
		
    	MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build(); 
    	
        return builder
        		.defaultAdvisors(new SimpleLoggerAdvisor(), messageChatMemoryAdvisor)
//                .defaultSystem("Summerize the response within 400 words.")
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("gpt-4o-mini") // or gpt-4.1 if you have access
                        .temperature(0.3)
                        .maxTokens(200)
                        .build())
                .build();
    }	

}
